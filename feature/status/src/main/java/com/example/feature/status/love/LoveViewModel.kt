package com.example.feature.status.love

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.common.NetworkResponseState
import com.example.core.common.mapper.ListMapper
import com.example.core.domain.model.entity.LoveCharacterEntity
import com.example.core.domain.repository.LoveCharacterRepository
import com.example.core.ui.model.LoveCharacter
import com.example.feature.status.StatusUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.core.ui.R as coreUiRes

@HiltViewModel
class LoveViewModel @Inject constructor(
    private val loveCharacterRepository: LoveCharacterRepository,
    private val loveCharacterMapper: ListMapper<LoveCharacterEntity, LoveCharacter>
) : ViewModel() {

    private val _getLoveCharacters = MutableLiveData<StatusUiState<LoveCharacter>>()
    val getLoveCharacters: LiveData<StatusUiState<LoveCharacter>> get() = _getLoveCharacters

    fun getLoveCharacters() = viewModelScope.launch(Dispatchers.IO)  {
        loveCharacterRepository.getLoveCharacters().collectLatest { response ->
            when (response) {
                is NetworkResponseState.Failure -> {
                    _getLoveCharacters.postValue(StatusUiState.Failure(coreUiRes.string.error))
                }
                is NetworkResponseState.Loading -> {
                    _getLoveCharacters.postValue(StatusUiState.Loading)
                }
                is NetworkResponseState.Success -> {
                    _getLoveCharacters.postValue(StatusUiState.Success(loveCharacterMapper.map(response.result)))
                }
            }
        }
    }

    fun removeFromLove(loveId: String) {
        viewModelScope.launch(Dispatchers.IO)  {
            loveCharacterRepository.removeFromLove(loveId)
        }
    }
}