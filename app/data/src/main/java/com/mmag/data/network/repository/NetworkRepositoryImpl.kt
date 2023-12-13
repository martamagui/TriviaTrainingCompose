package com.mmag.data.network.repository

import com.mmag.data.network.NetworkResponse
import com.mmag.data.network.TriviaService
import com.mmag.data.network.model.CategoryResponse
import com.mmag.data.network.model.QuizResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject


class NetworkRepositoryImpl @Inject constructor(
    private val service: TriviaService
) : NetworkRepository {

    override suspend fun getQuestions(
        number: Int,
        category: Int?
    ): Flow<NetworkResponse<QuizResponse>> = flow {
        emit(NetworkResponse.Loading())
        val response = service.getQuestions(number, category)
        if (response.isSuccessful) {
            emit(NetworkResponse.Success(response.body()))
        } else {
            emit(NetworkResponse.Error("Error de carga", null, response.code()))
        }
    }

    override suspend fun getCategories(): Flow<NetworkResponse<CategoryResponse>> = flow {
        emit(NetworkResponse.Loading())
        val response = service.getCategories()
        if (response.isSuccessful) {
            emit(NetworkResponse.Success(response.body()))
        } else {
            emit(NetworkResponse.Error("Error de carga", null, response.code()))
        }
    }

}