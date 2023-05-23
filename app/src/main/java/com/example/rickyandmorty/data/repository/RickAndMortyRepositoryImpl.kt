package com.example.rickyandmorty.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.dto.Character
import com.example.rickyandmorty.data.mapper.CharacterUiDataMapperImpl
import com.example.rickyandmorty.data.source.remote.RemoteDataSource
import com.example.rickyandmorty.di.coroutine.IoDispatcher
import com.example.rickyandmorty.domain.model.CharacterUiData
import com.example.rickyandmorty.domain.repository.RickAndMortyRepository
import com.example.rickyandmorty.utility.NameState
import com.example.rickyandmorty.utility.StatusState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val characterUiDataMapper: CharacterUiDataMapperImpl,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RickAndMortyRepository {
    override suspend fun getAllCharacters(name: NameState, status: StatusState): Flow<PagingData<CharacterUiData>> {
        return remoteDataSource.getAllCharacters(name, status).map { pagingData ->
            pagingData.map { character ->
                mapCharacter(character)
            }
        }
    }

    override suspend fun getCharacterById(characterId: String): Flow<NetworkResponseState<CharacterUiData>> {
        return flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getCharacterById(characterId)) {
                is NetworkResponseState.Failure -> {
                    emit(NetworkResponseState.Failure(response.exception))
                }
                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(mapCharacter(response.result)))
                    println(response.result)
                }
                else -> {}
            }
        }.flowOn(ioDispatcher)
    }


    private fun mapCharacter(mapCharacter: Character?): CharacterUiData {
        return characterUiDataMapper.map(mapCharacter)
    }



}