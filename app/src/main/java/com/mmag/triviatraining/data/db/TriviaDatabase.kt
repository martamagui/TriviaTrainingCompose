package com.mmag.triviatraining.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import com.mmag.triviatraining.data.db.model.CategoryLocal
import com.mmag.triviatraining.data.db.model.IncorrectAnswerLocal
import com.mmag.triviatraining.data.db.model.QuizQuestionLocal

@Database(
    entities = [
        QuizQuestionLocal::class,
        IncorrectAnswerLocal::class,
        CategoryLocal::class
    ],
    version = 1,
    exportSchema = false
)
abstract class TriviaDatabase : RoomDatabase() {
    abstract fun triviaDao(): TriviaDao
}
