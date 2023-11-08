package com.mmag.triviatraining.data.data_source

import android.util.Log
import com.mmag.triviatraining.data.db.model.CategoryLocal
import com.mmag.triviatraining.data.db.model.QuestionWithIncorrectAnswers
import com.mmag.triviatraining.data.db.model.toUIModel
import com.mmag.triviatraining.data.db.repository.DatabaseRepository
import com.mmag.triviatraining.data.network.NetworkResponse
import com.mmag.triviatraining.data.network.model.toLocal
import com.mmag.triviatraining.data.network.repository.NetworkRepository
import com.mmag.triviatraining.presentation.ui_model.QuizCategory
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import com.mmag.triviatraining.presentation.ui_model.TriviaResponse
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
                list.add(question.toUIModel())
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
                    categories.forEach { transformedList.add(it.toUIModel()) }
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



