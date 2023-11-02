package com.mmag.triviatraining.data.data_source

import com.mmag.triviatraining.presentation.ui_model.QuizQuestion

interface DataSourceRepository {

    suspend fun updateFromRemote()
    suspend fun getQuestions(): List<QuizQuestion>
}