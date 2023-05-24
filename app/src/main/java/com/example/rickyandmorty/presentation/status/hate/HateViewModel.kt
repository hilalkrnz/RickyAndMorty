package com.example.rickyandmorty.presentation.status.hate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmorty.R
import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.domain.repository.HateCharacterRepository
import com.example.rickyandmorty.presentation.status.StatusUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HateViewModel @Inject constructor(
    private val hateCharacterRepository: HateCharacterRepository
) : ViewModel() {

    private val _getHateCharacters = MutableLiveData<StatusUiState<HateCharacter>>()
    val getHateCharacters: LiveData<StatusUiState<HateCharacter>> get() = _getHateCharacters

    init {
        getHateCharacters()
    }

    private fun getHateCharacters() = viewModelScope.launch(Dispatchers.IO) {
        viewModelScope.launch {
            hateCharacterRepository.getHateCharacters().collectLatest { response ->
                when (response) {
                    is NetworkResponseState.Failure -> {
                        _getHateCharacters.postValue(StatusUiState.Failure(R.string.error))
                    }
                    is NetworkResponseState.Loading -> {
                        _getHateCharacters.postValue(StatusUiState.Loading)
                    }
                    is NetworkResponseState.Success -> {
                        _getHateCharacters.postValue(StatusUiState.Success(response.result))
                    }
                }
            }
        }
    }

    fun removeFromHate(favoriteId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            hateCharacterRepository.removeFromHate(favoriteId)
        }
    }
}