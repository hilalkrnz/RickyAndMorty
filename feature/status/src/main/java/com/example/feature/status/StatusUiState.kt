package com.example.feature.status

import androidx.annotation.StringRes

sealed class StatusUiState<out T : Any> {
    object Loading : StatusUiState<Nothing>()
    data class Success<out T : Any>(val data: List<T>?) : StatusUiState<T>()
    data class Failure(@StringRes val message: Int) : StatusUiState<Nothing>()
}