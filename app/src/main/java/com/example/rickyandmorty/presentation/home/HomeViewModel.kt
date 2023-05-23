package com.example.rickyandmorty.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.domain.usecase.GetAllCharactersUseCase
import com.example.rickyandmorty.utility.NameState
import com.example.rickyandmorty.utility.PagingLoadStateAdapter
import com.example.rickyandmorty.utility.StatusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase
) : ViewModel() {

    private val _selectedNameValue = MutableLiveData<NameState>()
    val selectedNameValue: LiveData<NameState> get() = _selectedNameValue

    private val _selectedStatusValue = MutableLiveData<StatusState>()
    val selectedStatusValue: LiveData<StatusState> get() = _selectedStatusValue

    suspend fun getAllCharacters(
        name: NameState,
        status: StatusState
    ): Flow<PagingData<CharacterUiData>> {
        return getAllCharactersUseCase(name, status).cachedIn(viewModelScope)
    }

    fun setSelectedNameValue(nameValue: NameState) {
        _selectedNameValue.value = nameValue
    }

    fun setSelectedStatusValue(statusValue: StatusState) {
        _selectedStatusValue.value = statusValue
    }

}