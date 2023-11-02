package com.mmag.triviatraining.data.db


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mmag.triviatraining.data.db.model.QuizQuestionLocal
import kotlinx.coroutines.flow.Flow

@Dao
interface TriviaDao {
    @Insert
    fun insertAllQuestions(list: List<QuizQuestionLocal>)

    @Query("SELECT *  FROM quiz_question")
    fun findAllQuestions(): Flow<QuizQuestionLocal>

    @Delete
    fun delete(questionLocal: QuizQuestionLocal)
}
