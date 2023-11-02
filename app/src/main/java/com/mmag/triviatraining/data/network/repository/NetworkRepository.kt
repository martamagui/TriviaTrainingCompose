package com.mmag.triviatraining.data.network.repository

import com.mmag.triviatraining.data.network.NetworkResponse
import com.mmag.triviatraining.data.network.model.CategoryResponse
import com.mmag.triviatraining.data.network.model.QuizResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface NetworkRepository {
    suspend fun getQuestions(number: Int, category: Int?): Flow<NetworkResponse<QuizResponse>>
    suspend fun getCategories(): Flow<NetworkResponse<CategoryResponse>>
}