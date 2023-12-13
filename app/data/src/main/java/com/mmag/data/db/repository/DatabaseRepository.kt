package com.mmag.data.db.repository

import com.mmag.data.db.model.CategoryLocal
import com.mmag.data.db.model.QuestionWithIncorrectAnswers
import com.mmag.data.db.model.QuizQuestionLocal
import com.mmag.data.network.model.QuizQuestionResponse
import kotlinx.coroutines.flow.Flow

interface DatabaseRepository {

    //region --- QuizQuestion
    suspend fun insertQuestions(list: List<QuizQuestionResponse>)

    suspend fun findAllQuestions(): Flow<List<QuestionWithIncorrectAnswers>>

    suspend fun findAQuestionsByCategory(categorySelected: String): Flow<List<QuestionWithIncorrectAnswers>>

    suspend fun delete(questionLocal: QuizQuestionLocal)
    //endregion --- QuizQuestion

    //region --- Category
    fun insertCategories(list: List<CategoryLocal>)
    fun getCategories(): Flow<List<CategoryLocal>?>
    //endregion --- Category
}