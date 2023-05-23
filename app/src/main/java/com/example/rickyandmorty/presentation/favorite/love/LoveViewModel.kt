package com.example.rickyandmorty.presentation.favorite.love

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickyandmorty.data.database.FavoriteCharacter
import com.example.rickyandmorty.domain.repository.FavoriteCharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoveViewModel @Inject constructor(
    private val favoriteCharacterRepository: FavoriteCharacterRepository
): ViewModel() {

    private val _getFavoriteCharacters = MutableLiveData<List<FavoriteCharacter>>()
    val getFavoriteCharacters: LiveData<List<FavoriteCharacter>> get() = _getFavoriteCharacters

    init {
        getFavoriteCharacters()
    }

    private fun getFavoriteCharacters() = viewModelScope.launch(Dispatchers.IO) {
        _getFavoriteCharacters.postValue(favoriteCharacterRepository.getFavoriteCharacters())
    }

    fun removeFromFavorite(favoriteId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            favoriteCharacterRepository.removeFromFavorite(favoriteId)
        }
    }
}