package com.mmag.triviatraining.data.storage.repository

import androidx.room.Delete
import androidx.room.Query
import com.mmag.triviatraining.data.db.model.QuizQuestionLocal
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {
    suspend fun insertAllQuestions(list: List<QuizQuestionLocal>)

    suspend fun findAllQuestions(): Flow<QuizQuestionLocal>

    suspend fun delete(questionLocal: QuizQuestionLocal)
}