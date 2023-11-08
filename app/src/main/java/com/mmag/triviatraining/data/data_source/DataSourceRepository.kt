package com.mmag.triviatraining.data.data_source

import com.mmag.triviatraining.presentation.ui_model.QuizCategory
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import com.mmag.triviatraining.presentation.ui_model.TriviaResponse
import kotlinx.coroutines.flow.Flow

interface DataSourceRepository {
    suspend fun updateQuestionsFromRemote(category: Int?)
    suspend fun getQuestionsByCategory(category: QuizCategory?): Flow<TriviaResponse<List<QuizQuestion>>>
    suspend fun getCategories(): Flow<TriviaResponse<List<QuizCategory>>>
}