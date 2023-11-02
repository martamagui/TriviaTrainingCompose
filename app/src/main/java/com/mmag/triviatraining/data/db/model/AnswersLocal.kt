package com.mmag.triviatraining.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion

@Entity(tableName = "quiz_answer")
data class IncorrectAnswerLocal(
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

fun QuestionWithIncorrectAnswers.toUIModel(): QuizQuestion {
    val incorrectAnswers: MutableList<String> = mutableListOf()
    this.incorrectAnswersList.forEach {
        incorrectAnswers.add(it.answer)
    }
    return QuizQuestion(
        id = question.id,
        category = question.category,
        correctAnswer = question.correctAnswer,
        difficulty = question.difficulty,
        incorrectAnswers = incorrectAnswers.toList(),
        question = question.question,
        type = question.type
    )

}