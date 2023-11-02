package com.mmag.triviatraining.data.network.repository

import com.mmag.triviatraining.data.network.NetworkResponse
import com.mmag.triviatraining.data.network.TriviaService
import com.mmag.triviatraining.data.network.model.QuizResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val service: TriviaService
) : NetworkRepository {

    override suspend fun getQuestions(number: Int): Flow<NetworkResponse<QuizResponse>> = flow {
        emit(NetworkResponse.Loading())
        val response = service.getQuestions(number)
        if (response.isSuccessful) {
            emit(NetworkResponse.Success(response.body()))
        } else {
            emit(NetworkResponse.Error("Error de carga", null, response.code()))
        }
    }


}