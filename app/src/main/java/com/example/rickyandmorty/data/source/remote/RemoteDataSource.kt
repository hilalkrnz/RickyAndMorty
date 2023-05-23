package com.example.rickyandmorty.data.source.remote

import androidx.paging.PagingData
import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.dto.Character
import com.example.rickyandmorty.utility.NameState
import com.example.rickyandmorty.utility.StatusState
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    suspend fun getAllCharacters(
        name: NameState,
        status: StatusState
    ): Flow<PagingData<Character>>

    suspend fun getCharacterById(characterId: String): NetworkResponseState<Character>
}