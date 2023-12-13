package com.mmag.data.data_source

import android.util.Log
import com.mmag.data.db.model.CategoryLocal
import com.mmag.data.db.model.QuestionWithIncorrectAnswers
import com.mmag.data.db.model.toDomainModel
import com.mmag.data.db.repository.DatabaseRepository
import com.mmag.data.network.NetworkResponse
import com.mmag.data.network.model.toLocal
import com.mmag.data.network.repository.NetworkRepository
import com.mmag.domain.model.QuizCategory
import com.mmag.domain.model.QuizQuestion
import com.mmag.domain.model.TriviaResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DataSourceRepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository
) : DataSourceRepository {

    private val tag = "DataSourceRepositoryImpl"

    //region --- Questions
    override suspend fun updateQuestionsFromRemote(category: Int?) {
        var quantity = 10
        if (category == null) {
            quantity = 50
        }
        val response = networkRepository.getQuestions(quantity, category)
        response.collect { response ->
            when (response) {
                is NetworkResponse.Error -> {
                    Log.e("Error", "${response.message}")
                }

                is NetworkResponse.Loading -> {}
                is NetworkResponse.Success -> {
                    if (response.data != null) {
                        databaseRepository.insertQuestions(response.data.questionList)
                    }
                }
            }
        }
    }

    override suspend fun getQuestionsByCategory(category: QuizCategory?): Flow<TriviaResponse<List<QuizQuestion>>> =
        flow {
            emit(TriviaResponse.Loading())
            if (category == null) {
                databaseRepository.findAllQuestions().collect { dbResponse ->
                    handleDatabaseResponse(
                        dbResponse,
                        this,
                        this@DataSourceRepositoryImpl,
                        null
                    )
                }
            } else {
                withContext(Dispatchers.IO) {
                    updateQuestionsFromRemote(category.id)
                }
                databaseRepository.findAQuestionsByCategory(category.name).collect { dbResponse ->
                    handleDatabaseResponse(
                        dbResponse,
                        this,
                        this@DataSourceRepositoryImpl,
                        category
                    )
                }
            }
        }.catch {
            Log.e(tag, "${it.message}")
            emit(TriviaResponse.Error(it.message))
        }

    private suspend fun handleDatabaseResponse(
        dbResponse: List<QuestionWithIncorrectAnswers>,
        flowCollector: FlowCollector<TriviaResponse<List<QuizQuestion>>>,
        dataSourceRepositoryImpl: DataSourceRepositoryImpl,
        category: QuizCategory?
    ) {
        if (dbResponse.isNotEmpty()) {
            val list = mutableSetOf<QuizQuestion>()
            dbResponse.forEach { question ->
                list.add(question.toDomainModel())
            }
            flowCollector.emit(TriviaResponse.Success(list.toList()))
        } else {
            //TODO poner el que emita el error si falla la petición de red
            dataSourceRepositoryImpl.updateQuestionsFromRemote(category?.id)
        }
    }
    //endregion --- Questions

    //region --- Categories
    private suspend fun updateCategoriesFromRemote() {
        networkRepository.getCategories().collect { response ->
            when (response) {
                is NetworkResponse.Error -> {
                    //TODO poner el que emita el error si falla la petición de red
                    Log.e("Error", "${response.message}")
                }

                is NetworkResponse.Loading -> {
                    Log.i("Cargando..", "Cargando categorías...")
                }

                is NetworkResponse.Success -> {
                    if (response.data != null) {
                        val transformedList = mutableListOf<CategoryLocal>()
                        response.data.triviaCategories.forEach {
                            transformedList.add(it.toLocal())
                        }
                        databaseRepository.insertCategories(transformedList)
                    }
                }
            }
        }
    }

    override suspend fun getCategories(): Flow<TriviaResponse<List<QuizCategory>>> = flow {
        databaseRepository.getCategories().collect { categories ->
            emit(TriviaResponse.Loading<List<QuizCategory>>())
            if (categories != null) {
                if (categories.isNotEmpty()) {
                    val transformedList = mutableListOf<QuizCategory>()
                    categories.forEach { transformedList.add(it.toDomainModel()) }
                    emit(TriviaResponse.Success(transformedList.toList()))
                } else {
                    //TODO poner el que emita el error si falla la petición de red
                    updateCategoriesFromRemote()
                }
            }
        }
    }.catch {
        Log.e(tag, "${it.message}")
        emit(TriviaResponse.Error(it.message))
    }
    //endregion --- Categories

}



