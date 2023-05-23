package com.example.rickyandmorty.domain.repository

import androidx.paging.PagingData
import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.utility.NameState
import com.example.rickyandmorty.utility.StatusState
import kotlinx.coroutines.flow.Flow


interface RickAndMortyRepository {

    suspend fun getAllCharacters(name: NameState, status: StatusState): Flow<PagingData<CharacterUiData>>

    suspend fun getCharacterById(characterId: String): Flow<NetworkResponseState<CharacterUiData>>



}
