package com.example.feature.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.NetworkResponseState
import com.example.core.common.mapper.Mapper
import com.example.core.domain.model.CharacterDomainData
import com.example.core.domain.model.entity.HateCharacterEntity
import com.example.core.domain.model.entity.LoveCharacterEntity
import com.example.core.domain.repository.HateCharacterRepository
import com.example.core.domain.repository.LoveCharacterRepository
import com.example.core.domain.usecase.GetCharacterByIdUseCase
import com.example.core.ui.CharacterUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.core.ui.R as coreUiRes
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCharacterByIdUseCase: GetCharacterByIdUseCase,
    private val loveCharacterRepository: LoveCharacterRepository,
    private val hateCharacterRepository: HateCharacterRepository,
    private val characterMapper: Mapper<CharacterDomainData, CharacterUiData>
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
                        _rickAndMortyDetailUiState.postValue(DetailUiState.Failure(coreUiRes.string.error))
                    }
                    is NetworkResponseState.Loading -> {
                        _rickAndMortyDetailUiState.postValue(DetailUiState.Loading)
                    }
                    is NetworkResponseState.Success -> {
                        _rickAndMortyDetailUiState.postValue(DetailUiState.Success(characterMapper.map(response.result)))
                    }
                }
            }
        }
    }

    fun addToFavorite(character: CharacterUiData) {
        viewModelScope.launch {
            loveCharacterRepository.addToLove(
                LoveCharacterEntity(
                    characterId = character.id!!,
                    characterName = character.name,
                    characterImage = character.image
                )
            )
        }
    }

    fun checkFavoriteCharacter(id: String) {
        viewModelScope.launch {
            val count = loveCharacterRepository.checkLoveCharacter(id)
            _isFavorite.postValue(count > 0)
        }
    }

    fun removeFromFavorite(favoriteId: String) {
        viewModelScope.launch {
            loveCharacterRepository.removeFromLove(favoriteId)
        }
    }

    fun addToHate(character: CharacterUiData) {
        viewModelScope.launch {
            hateCharacterRepository.addToHate(
                HateCharacterEntity(
                    characterId = character.id!!,
                    characterName = character.name,
                    characterImage = character.image
                )
            )
        }
    }

    fun checkHateCharacter(hateId: String) {
        viewModelScope.launch {
            val count = hateCharacterRepository.checkHateCharacter(hateId)
            _isHate.postValue(count > 0)
        }
    }

    fun removeFromHate(hateId: String) {
        viewModelScope.launch {
            hateCharacterRepository.removeFromHate(hateId)
        }
    }


}