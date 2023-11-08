package com.mmag.triviatraining.presentation.ui_model

sealed class TriviaResponse<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : TriviaResponse<T>()

    class Empty<T> : TriviaResponse<T>()

    class Success<T>(data: T?) : TriviaResponse<T>(data)

    class Error<T>(message: String?, data: T? = null) :
        TriviaResponse<T>(data, message)
}
