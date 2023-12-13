package com.mmag.data.network.model


import com.google.gson.annotations.SerializedName
import com.mmag.data.db.model.QuizQuestionLocal
import com.mmag.domain.model.DifficultyInterface
import com.mmag.utils.cleanText

data class QuizQuestionResponse(
    @SerializedName("category")
    val category: String,
    @SerializedName("correct_answer")
    val correctAnswer: String,
    @SerializedName("difficulty")
    val difficulty: Difficulty,
    @SerializedName("incorrect_answers")
    val incorrectAnswers: List<String>,
    @SerializedName("question")
    val question: String,
    @SerializedName("type")
    val type: String
)

enum class Difficulty : DifficultyInterface {
    @SerializedName("hard")
    HARD {
        override fun text(): String = "hard"
    },

    @SerializedName("medium")
    MEDIUM {
        override fun text(): String = "medium"
    },

    @SerializedName("easy")
    EASY {
        override fun text(): String = "easy"
    }
}



fun QuizQuestionResponse.toLocal(): QuizQuestionLocal {
    return QuizQuestionLocal(
        0,
        category = category,
        difficulty = difficulty.text(),
        question = question.cleanText(),
        correctAnswer = correctAnswer.cleanText(),
        type = type
    )
}