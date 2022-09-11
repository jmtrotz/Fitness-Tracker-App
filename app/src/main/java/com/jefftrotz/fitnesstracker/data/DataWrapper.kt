package com.jefftrotz.fitnesstracker.data

sealed class DataWrapper<T>(val data: T? = null, val message: String? = null){
    class Success<T>(data: T): DataWrapper<T>(data)
    class Loading<T>(isLoading: T? = null): DataWrapper<T>(isLoading)
    class Error<T>(message: String?, data: T? = null): DataWrapper<T>(data, message)
}