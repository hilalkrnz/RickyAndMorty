package com.example.feature.detail

import androidx.annotation.StringRes
import com.example.core.ui.model.CharacterUiData

sealed class DetailUiState {
    object Loading : DetailUiState()
    data class Success(val data: CharacterUiData?) : DetailUiState()
    data class Failure(@StringRes val message: Int) : DetailUiState()
}
