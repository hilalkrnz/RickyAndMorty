package com.example.rickyandmorty.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmorty.R
import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.FavoriteCharacter
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.domain.repository.FavoriteCharacterRepository
import com.example.rickyandmorty.domain.repository.HateCharacterRepository
import com.example.rickyandmorty.domain.usecase.GetCharacterByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val favoriteCharacterRepository: FavoriteCharacterRepository,
    private val hateCharacterRepository: HateCharacterRepository
) : ViewModel() {

    private val _rickAndMortyDetailUiState = MutableLiveData<DetailUiState>()
    val rickAndMortyDetailUiState: LiveData<DetailUiState> get() = _rickAndMortyDetailUiState

    private val _isFavorite = MutableLiveData(false)
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    private val _isHate = MutableLiveData(false)
    val isHate: LiveData<Boolean> get() = _isHate


    fun getCharacterById(characterId: String) {
        viewModelScope.launch {
            getCharacterByIdUseCase(characterId).collectLatest { response ->
                when (response) {
                    is NetworkResponseState.Failure -> {
                        _rickAndMortyDetailUiState.postValue(DetailUiState.Failure(R.string.error))
                    }
                    is NetworkResponseState.Loading -> {
                        _rickAndMortyDetailUiState.postValue(DetailUiState.Loading)
                    }
                    is NetworkResponseState.Success -> {
                        _rickAndMortyDetailUiState.postValue(DetailUiState.Success(response.result))
                    }
                }
            }
        }
    }

    fun addToFavorite(character: CharacterUiData) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteCharacterRepository.addToFavorite(
                FavoriteCharacter(
                    characterId = character.id!!,
                    characterName = character.name,
                    characterImage = character.image
                )
            )
        }
    }

    fun checkFavoriteCharacter(id: String) {
        viewModelScope.launch {
            val count = favoriteCharacterRepository.checkFavoriteCharacter(id)
            _isFavorite.postValue(count > 0)
        }
    }

    fun removeFromFavorite(favoriteId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteCharacterRepository.removeFromFavorite(favoriteId)
        }
    }

    fun addToHate(character: CharacterUiData) {
        viewModelScope.launch(Dispatchers.IO) {
            hateCharacterRepository.addToHate(
                HateCharacter(
                    characterId = character.id!!,
                    characterName = character.name,
                    characterImage = character.image
                )
            )
        }
    }

    fun checkHateCharacter(hateId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val count = hateCharacterRepository.checkHateCharacter(hateId)
            _isHate.postValue(count > 0)
        }
    }

    fun removeFromHate(hateId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            hateCharacterRepository.removeFromHate(hateId)
        }
    }


}