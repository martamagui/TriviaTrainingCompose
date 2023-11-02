package com.mmag.triviatraining.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_question")
data class QuizQuestionLocal(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "category")
    val category: String,
    @ColumnInfo(name = "difficulty")
    val difficulty: String,
    @ColumnInfo(name = "question")
    val question: String,
    @ColumnInfo(name = "type")
    val type: String
)
