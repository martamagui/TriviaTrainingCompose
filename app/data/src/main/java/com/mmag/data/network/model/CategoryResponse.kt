package com.mmag.data.network.model


import com.google.gson.annotations.SerializedName
import com.mmag.data.db.model.CategoryLocal

data class CategoryResponse(
    @SerializedName("trivia_categories")
    val triviaCategories: List<TriviaCategory>
)
