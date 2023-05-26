package com.example.core.domain.repository

import androidx.paging.PagingData
import com.example.core.common.NetworkResponseState
import com.example.core.common.utils.NameState
import com.example.core.common.utils.StatusState
import com.example.core.domain.model.CharacterDomainData
import kotlinx.coroutines.flow.Flow


interface RickAndMortyRepository {

    suspend fun getAllCharacters(
        name: NameState,
        status: StatusState
    ): Flow<PagingData<CharacterDomainData>>

    suspend fun getCharacterById(characterId: String): Flow<NetworkResponseState<CharacterDomainData>>


}
