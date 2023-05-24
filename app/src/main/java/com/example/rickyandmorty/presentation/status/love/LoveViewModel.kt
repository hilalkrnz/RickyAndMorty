package com.example.rickyandmorty.presentation.status.love

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmorty.R
import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.LoveCharacter
import com.example.rickyandmorty.domain.repository.LoveCharacterRepository
import com.example.rickyandmorty.presentation.status.StatusUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoveViewModel @Inject constructor(
    private val loveCharacterRepository: LoveCharacterRepository
) : ViewModel() {

    private val _getLoveCharacters = MutableLiveData<StatusUiState<LoveCharacter>>()
    val getLoveCharacters: LiveData<StatusUiState<LoveCharacter>> get() = _getLoveCharacters

    init {
        getLoveCharacters()
    }

    private fun getLoveCharacters() {
        viewModelScope.launch {
            loveCharacterRepository.getLoveCharacters().collectLatest { response ->
                when (response) {
                    is NetworkResponseState.Failure -> {
                        _getLoveCharacters.postValue(StatusUiState.Failure(R.string.error))
                    }
                    is NetworkResponseState.Loading -> {
                        _getLoveCharacters.postValue(StatusUiState.Loading)
                    }
                    is NetworkResponseState.Success -> {
                        _getLoveCharacters.postValue(StatusUiState.Success(response.result))
                    }
                }
            }
        }
    }

    fun removeFromLove(loveId: String) {
        viewModelScope.launch {
            loveCharacterRepository.removeFromLove(loveId)
        }
    }
}