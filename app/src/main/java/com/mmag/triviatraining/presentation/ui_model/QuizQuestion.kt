package com.mmag.triviatraining.presentation.ui_model

import com.mmag.triviatraining.data.db.model.QuestionWithIncorrectAnswers
import com.mmag.triviatraining.data.network.model.DifficultyInterface

data class QuizQuestion(
    val id: Long,
    val category: String,
    val correctAnswer: String,
    val difficulty: String,
    val allAnswers: List<String>,
    val question: String,
    val type: String
)


enum class DifficultyUI(value: String) : DifficultyInterface {
    HARD("hard") {
        override fun text(): String = "hard"
    },
    MEDIUM("medium") {
        override fun text(): String = "medium"
    },
    EASY("easy") {
        override fun text(): String = "easy"
    }
}