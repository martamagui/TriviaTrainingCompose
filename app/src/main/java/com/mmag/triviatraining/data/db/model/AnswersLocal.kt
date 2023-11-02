package com.mmag.triviatraining.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "quiz_answer")
data class IncorrectAnswerLocal (
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    @ColumnInfo(name = "question_id")
    val questionId: Long,
    @ColumnInfo(name = "answer")
    val answer: String,
)

data class QuestionWithIncorrectAnswers(
    @Embedded val question: QuizQuestionLocal,
    @Relation(
        parentColumn = "id",
        entityColumn = "question_id"
    )
    val incorrectAnswersList: List<IncorrectAnswerLocal>
)
