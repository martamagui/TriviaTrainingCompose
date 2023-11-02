package com.mmag.triviatraining.data.network.repository

import com.mmag.triviatraining.data.network.NetworkResponse
import com.mmag.triviatraining.data.network.model.QuizResponse
import kotlinx.coroutines.flow.Flow

interface NetworkRepository {
    suspend fun getQuestions(number: Int): Flow<NetworkResponse<QuizResponse>>
}