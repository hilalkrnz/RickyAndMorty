package com.example.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.core.common.mapper.Mapper
import com.example.core.common.utils.NameState
import com.example.core.common.utils.StatusState
import com.example.core.domain.model.CharacterDomainData
import com.example.core.domain.usecase.GetAllCharactersUseCase
import com.example.core.ui.model.CharacterUiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllCharactersUseCase: GetAllCharactersUseCase,
    private val characterMapper: Mapper<CharacterDomainData, CharacterUiData>
) : ViewModel() {
    fun getAllCharacters(
        name: NameState,
        status: StatusState
    ): Flow<PagingData<CharacterUiData>> {
        return getAllCharactersUseCase(name, status)
            .map { pagingData ->
                pagingData.map { characterDomainData ->
                    characterMapper.map(characterDomainData)
                }
            }
            .cachedIn(viewModelScope)
    }
}