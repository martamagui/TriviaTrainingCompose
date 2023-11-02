package com.mmag.triviatraining.data.network.model


import com.google.gson.annotations.SerializedName
import com.mmag.triviatraining.data.db.model.CategoryLocal

data class TriviaCategory(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun TriviaCategory.toLocal() = CategoryLocal(id, name)
