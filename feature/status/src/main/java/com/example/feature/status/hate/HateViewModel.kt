package com.example.feature.status.hate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.DataResponseState
import com.example.core.common.mapper.ListMapper
import com.example.core.domain.model.entity.HateCharacterEntity
import com.example.core.domain.repository.HateCharacterRepository
import com.example.core.ui.model.HateCharacter
import com.example.feature.status.StatusUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.core.ui.R as coreUiRes

@HiltViewModel
class HateViewModel @Inject constructor(
    private val hateCharacterRepository: HateCharacterRepository,
    private val hateCharacterMapper: ListMapper<HateCharacterEntity, HateCharacter>
) : ViewModel() {

    private val _getHateCharacters = MutableLiveData<StatusUiState<HateCharacter>>()
    val getHateCharacters: LiveData<StatusUiState<HateCharacter>> get() = _getHateCharacters


     fun getHateCharacters() {
         viewModelScope.launch(Dispatchers.Main) {
             println("Thread " + Thread.currentThread().name)
             hateCharacterRepository.getHateCharacters().collectLatest { response ->
                 println("Thread repo " + Thread.currentThread().name)
                 when (response) {
                     is DataResponseState.Failure -> {
                         _getHateCharacters.postValue(StatusUiState.Failure(coreUiRes.string.error))
                     }

                     is DataResponseState.Loading -> {
                         _getHateCharacters.postValue(StatusUiState.Loading)
                     }

                     is DataResponseState.Success -> {
                         println("Thread 3 " + Thread.currentThread().name)
                         _getHateCharacters.postValue(
                             StatusUiState.Success(hateCharacterMapper.map(response.result))
                         )
                     }
                 }
             }
         }
    }

    fun removeFromHate(favoriteId: String) {
        viewModelScope.launch {
            hateCharacterRepository.removeFromHate(favoriteId)
            getHateCharacters()
        }
    }
}