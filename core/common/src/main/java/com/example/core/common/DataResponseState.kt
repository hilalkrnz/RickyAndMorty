package com.example.core.common

sealed class DataResponseState<out T : Any> {
    object Loading : DataResponseState<Nothing>()
    data class Failure(val exception: Exception) : DataResponseState<Nothing>()
    data class Success<out T : Any>(val result: T?) : DataResponseState<T>()
}