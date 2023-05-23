package com.example.rickyandmorty.presentation.favorite.hate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.domain.repository.HateCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HateViewModel @Inject constructor(
    private val hateCharacterRepository: HateCharacterRepository
): ViewModel() {

    private val _getHateCharacters = MutableLiveData<List<HateCharacter>>()
    val getHateCharacters: LiveData<List<HateCharacter>> get() = _getHateCharacters

    init {
        getHateCharacters()
    }

    private fun getHateCharacters() = viewModelScope.launch(Dispatchers.IO) {
    _getHateCharacters.postValue(hateCharacterRepository.getHateCharacters())
    }

    fun removeFromHate(favoriteId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            hateCharacterRepository.removeFromHate(favoriteId)
        }
    }
}