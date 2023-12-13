package com.mmag.data.network

import com.mmag.data.network.model.QuizResponse

sealed class NetworkResponse<T>(
    val data: T? = null,
    val message: String? = null,
    val code: Int? = null
) {
    class Success<T>(data: T?) : NetworkResponse<T>(data)

    class Error<T>(message: String?, data: T? = null, code: Int? = null) :
        NetworkResponse<T>(data, message, code)

    class Loading<T> : NetworkResponse<T>()
}
