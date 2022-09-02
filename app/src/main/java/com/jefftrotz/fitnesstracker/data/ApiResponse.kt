package com.jefftrotz.fitnesstracker.data

sealed class ApiResponse<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T): ApiResponse<T>(data)
    class Loading<T>(isLoading: T? = null): ApiResponse<T>(isLoading)
    class Error<T>(message: String?, data: T? = null): ApiResponse<T>(data, message)
}