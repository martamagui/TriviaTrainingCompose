package com.mmag.triviatraining.data.network.repository

import com.mmag.triviatraining.data.network.NetworkAPI
import com.mmag.triviatraining.data.network.TriviaService
import javax.inject.Inject
import javax.inject.Singleton


class NetworkRepositoryImpl @Inject constructor(private val service: TriviaService) :
    NetworkRepository {


}