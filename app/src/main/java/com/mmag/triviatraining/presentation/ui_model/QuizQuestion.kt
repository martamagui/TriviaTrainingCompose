package com.mmag.triviatraining.presentation.ui_model

import com.google.gson.annotations.SerializedName
import com.mmag.triviatraining.data.network.model.Difficulty

data class QuizQuestion(
    val id: Int,
    val category: String,
    val correctAnswer: String,
    val difficulty: Difficulty,
    val incorrectAnswers: List<String>,
    val question: String,
    val type: String
)

