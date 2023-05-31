package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.common.DataResponseState
import com.example.core.common.utils.NameState
import com.example.core.common.utils.StatusState
import com.example.core.domain.model.CharacterDomainData
import kotlinx.coroutines.flow.Flow


interface RickAndMortyRepository {

    fun getAllCharacters(
        name: NameState,
        status: StatusState
    ): Flow<PagingData<CharacterDomainData>>

    fun getCharacterById(characterId: String): Flow<DataResponseState<CharacterDomainData>>
}