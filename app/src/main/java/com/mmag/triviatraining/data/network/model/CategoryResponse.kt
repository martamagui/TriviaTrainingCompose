package com.mmag.triviatraining.data.network.model


import com.google.gson.annotations.SerializedName
import com.mmag.triviatraining.data.db.model.CategoryLocal

data class CategoryResponse(
    @SerializedName("trivia_categories")
    val triviaCategories: List<TriviaCategory>
)
