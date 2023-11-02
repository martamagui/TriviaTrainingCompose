package com.mmag.triviatraining.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mmag.triviatraining.presentation.ui_model.QuizCategory

@Entity(tableName = "quiz_category")
data class CategoryLocal(
    @PrimaryKey()
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("name")
    val name: String
)

fun CategoryLocal.toUIModel(): QuizCategory = QuizCategory(id, name)