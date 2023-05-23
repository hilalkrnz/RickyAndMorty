package com.example.rickyandmorty.presentation.detail

import androidx.annotation.StringRes
import com.example.rickyandmorty.domain.model.CharacterUiData

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val data: CharacterUiData?) : DetailUiState()
    data class Failure(@StringRes val message: Int) : DetailUiState()
}
