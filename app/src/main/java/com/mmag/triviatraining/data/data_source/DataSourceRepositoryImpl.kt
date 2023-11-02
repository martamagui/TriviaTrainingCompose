package com.mmag.triviatraining.data.data_source

import android.util.Log
import com.mmag.triviatraining.data.db.model.toUIModel
import com.mmag.triviatraining.data.db.repository.DatabaseRepository
import com.mmag.triviatraining.data.network.NetworkResponse
import com.mmag.triviatraining.data.network.repository.NetworkRepository
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.withContext
import javax.inject.Inject


class DataSourceRepositoryImpl @Inject constructor(
    private val networkRepository: NetworkRepository,
    private val databaseRepository: DatabaseRepository
) : DataSourceRepository {

    override suspend fun updateFromRemote() = withContext(Dispatchers.IO) {
        val response = networkRepository.getQuestions(10)
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

    override suspend fun getQuestions(): List<QuizQuestion> {
        val list = mutableSetOf<QuizQuestion>()
        databaseRepository.findAllQuestions().forEach { question ->
            list.add(question.toUIModel())
        }
        return list.toList()
    }
}



