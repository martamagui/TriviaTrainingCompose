package com.mmag.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.mmag.domain.model.QuizQuestion

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

fun QuestionWithIncorrectAnswers.toDomainModel(): QuizQuestion {
    val allAnswers: MutableList<String> = mutableListOf()
    this.incorrectAnswersList.forEach {
        allAnswers.add(it.answer)
    }
    allAnswers.add(question.correctAnswer)
    allAnswers.shuffle()

    return QuizQuestion(
        id = question.id,
        category = question.category,
        correctAnswer = question.correctAnswer,
        difficulty = question.difficulty,
        question = question.question,
        type = question.type,
        allAnswers = allAnswers.toList()
    )
}