package com.mmag.triviatraining.data.data_source

import com.mmag.triviatraining.data.network.model.QuizQuestionResponse
import com.mmag.triviatraining.data.network.model.TriviaCategory
import com.mmag.triviatraining.presentation.ui_model.QuizCategory
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import kotlinx.coroutines.flow.Flow

interface DataSourceRepository {
    suspend fun updateQuestionsFromRemote(category: Int?)
    suspend fun getQuestionsByCategory(category: QuizCategory?): Flow<List<QuizQuestion>>
    suspend fun getCategories(): Flow<List<QuizCategory>>
}