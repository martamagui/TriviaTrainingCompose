package com.mmag.triviatraining.data.db


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.mmag.triviatraining.data.db.model.CategoryLocal
import com.mmag.triviatraining.data.db.model.IncorrectAnswerLocal
import com.mmag.triviatraining.data.db.model.QuestionWithIncorrectAnswers
import com.mmag.triviatraining.data.db.model.QuizQuestionLocal
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import kotlinx.coroutines.flow.Flow

@Dao
interface TriviaDao {
    //region --- QuizQuestion
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertQuestion(question: QuizQuestionLocal): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllAnswer(list: List<IncorrectAnswerLocal>)

    @Transaction
    @Query("SELECT *  FROM quiz_question")
    fun findAllQuestions(): Flow<List<QuestionWithIncorrectAnswers>>

    @Transaction
    @Query("SELECT *  FROM quiz_question WHERE category LIKE :categorySelected")
    fun findAQuestionsByCategory(categorySelected: String): Flow<List<QuestionWithIncorrectAnswers>>

    @Delete
    fun delete(questionLocal: QuizQuestionLocal)
    //endregion --- QuizQuestion

    //region --- Category
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(list: List<CategoryLocal>)

    @Query("SELECT * FROM quiz_category")
    fun getCategories(): Flow<List<CategoryLocal>>
    //endregion --- Category

}
