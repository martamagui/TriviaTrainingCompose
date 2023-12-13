package com.mmag.data.data_source

import com.mmag.domain.model.QuizCategory
import com.mmag.domain.model.QuizQuestion
import com.mmag.domain.model.TriviaResponse
import kotlinx.coroutines.flow.Flow

interface DataSourceRepository {
    suspend fun updateQuestionsFromRemote(category: Int?)
    suspend fun getQuestionsByCategory(category: QuizCategory?): Flow<TriviaResponse<List<QuizQuestion>>>
    suspend fun getCategories(): Flow<TriviaResponse<List<QuizCategory>>>
}