package com.mmag.triviatraining.data.network

import com.mmag.triviatraining.data.network.model.QuizResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaService {
    @GET("api.php")
    suspend fun getQuestions(@Query("amount") number:Int): Response<QuizResponse>
}