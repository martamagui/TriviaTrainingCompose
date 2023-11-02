package com.mmag.triviatraining.data.storage.repository

import com.mmag.triviatraining.data.db.TriviaDao
import com.mmag.triviatraining.data.db.model.QuizQuestionLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val dao: TriviaDao):DatabaseRepository {
    override suspend fun insertAllQuestions(list: List<QuizQuestionLocal>) {
        dao.insertAllQuestions(list)
    }

    override suspend fun findAllQuestions(): Flow<QuizQuestionLocal> {
        return dao.findAllQuestions()
    }

    override suspend fun delete(questionLocal: QuizQuestionLocal) {
        return dao.delete(questionLocal)
    }
}