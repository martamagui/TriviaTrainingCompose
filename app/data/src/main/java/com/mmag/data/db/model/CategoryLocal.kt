package com.mmag.data.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.mmag.domain.model.QuizCategory

@Entity(tableName = "quiz_category")
data class CategoryLocal(
    @PrimaryKey()
    @ColumnInfo("id")
    val id: Int,
    @ColumnInfo("name")
    val name: String
)

fun CategoryLocal.toDomainModel(): QuizCategory = QuizCategory(id, name)