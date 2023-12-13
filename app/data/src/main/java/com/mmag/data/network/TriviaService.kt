package com.mmag.data.network

import com.mmag.data.network.model.CategoryResponse
import com.mmag.data.network.model.QuizResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TriviaService {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") number: Int,
        @Query("category") categoryId: Int?,
    ): Response<QuizResponse>

    @GET("api_category.php")
    suspend fun getCategories(): Response<CategoryResponse>
}