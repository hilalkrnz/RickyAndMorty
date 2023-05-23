package com.example.rickyandmorty.data.source.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.api.RickAndMortyApi
import com.example.rickyandmorty.data.dto.Character
import com.example.rickyandmorty.utility.NameState
import com.example.rickyandmorty.utility.StatusState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    private val rickAndMortyApi: RickAndMortyApi
) : RemoteDataSource {
    override suspend fun getAllCharacters(
        name: NameState,
        status: StatusState
    ): Flow<PagingData<Character>> =
        Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                CharacterPagingSource(rickAndMortyApi, name, status)
            }
        ).flow


    override suspend fun getCharacterById(characterId: String): NetworkResponseState<Character> =
        try {
            val response = rickAndMortyApi.getCharacterById(characterId)
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Failure(e)
        }
}

const val NETWORK_PAGE_SIZE = 1