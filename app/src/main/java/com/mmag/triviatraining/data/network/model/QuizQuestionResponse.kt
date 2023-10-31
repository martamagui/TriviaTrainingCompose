package com.mmag.triviatraining.data.network.model


import com.google.gson.annotations.SerializedName

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
        override fun text(): String = "eady"
    }
}

interface DifficultyInterface {
    fun text(): String
}