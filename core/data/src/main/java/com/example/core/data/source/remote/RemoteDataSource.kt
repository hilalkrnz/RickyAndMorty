package com.example.core.data.source.remote

import androidx.paging.PagingData
import com.example.core.common.DataResponseState
import com.example.core.common.utils.NameState
import com.example.core.common.utils.StatusState
import com.example.core.data.dto.Character
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllCharacters(
        name: NameState,
        status: StatusState
    ): Flow<PagingData<Character>>

    suspend fun getCharacterById(characterId: String): DataResponseState<Character>
}