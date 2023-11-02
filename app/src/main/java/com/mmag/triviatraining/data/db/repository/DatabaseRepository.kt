package com.mmag.triviatraining.data.db.repository

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mmag.triviatraining.data.db.model.CategoryLocal
import com.mmag.triviatraining.data.db.model.QuestionWithIncorrectAnswers
import com.mmag.triviatraining.data.db.model.QuizQuestionLocal
import com.mmag.triviatraining.data.network.model.QuizQuestionResponse
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