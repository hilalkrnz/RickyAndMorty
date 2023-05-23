package com.example.rickyandmorty.data

sealed class NetworkResponseState<out T : Any> {

    object Loading : NetworkResponseState<Nothing>()
    data class Failure(val exception: Exception) : NetworkResponseState<Nothing>()
    data class Success<out T : Any>(val result: T?) : NetworkResponseState<T>()
}