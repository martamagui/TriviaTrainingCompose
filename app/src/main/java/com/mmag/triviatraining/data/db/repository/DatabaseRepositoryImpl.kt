package com.mmag.triviatraining.data.db.repository

import com.mmag.triviatraining.data.db.TriviaDao
import com.mmag.triviatraining.data.db.model.IncorrectAnswerLocal
import com.mmag.triviatraining.data.db.model.QuestionWithIncorrectAnswers
import com.mmag.triviatraining.data.db.model.QuizQuestionLocal
import com.mmag.triviatraining.data.network.model.QuizQuestionResponse
import com.mmag.triviatraining.data.network.model.toLocal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DatabaseRepositoryImpl @Inject constructor(private val dao: TriviaDao) : DatabaseRepository {

    override suspend fun insertQuestions(list: List<QuizQuestionResponse>) {
        list.forEach { question ->
            val savedQuestion = dao.insertQuestion(question.toLocal())
            val incorrectAnswers = mutableListOf<IncorrectAnswerLocal>()
            question.incorrectAnswers.forEach { answer ->
                val answer = IncorrectAnswerLocal(0, savedQuestion, answer)
                incorrectAnswers.add(answer)
            }
            dao.insertAllAnswer(incorrectAnswers)
        }
    }

    override suspend fun findAllQuestions(): Flow<QuestionWithIncorrectAnswers> {
        return dao.findAllQuestions()
    }

    override suspend fun delete(questionLocal: QuizQuestionLocal) {
        return dao.delete(questionLocal)
    }
}