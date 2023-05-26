package com.example.core.data.repository

import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.common.NetworkResponseState
import com.example.core.common.coroutine.IoDispatcher
import com.example.core.common.utils.NameState
import com.example.core.common.utils.StatusState
import com.example.core.data.mapper.CharacterDomainDataMapperImpl
import com.example.core.data.source.remote.RemoteDataSource
import com.example.core.domain.model.CharacterDomainData
import com.example.core.domain.repository.RickAndMortyRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import com.example.core.data.dto.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RickAndMortyRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val characterUiDataMapper: CharacterDomainDataMapperImpl,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : RickAndMortyRepository {
    override suspend fun getAllCharacters(name: NameState, status: StatusState): Flow<PagingData<CharacterDomainData>> {
        return remoteDataSource.getAllCharacters(name, status).map { pagingData ->
            pagingData.map { character ->
                mapCharacter(character)
            }
        }
    }

    override suspend fun getCharacterById(characterId: String): Flow<NetworkResponseState<CharacterDomainData>> {
        return flow {
            emit(NetworkResponseState.Loading)
            when (val response = remoteDataSource.getCharacterById(characterId)) {
                is NetworkResponseState.Failure -> {
                    emit(NetworkResponseState.Failure(response.exception))
                }
                is NetworkResponseState.Success -> {
                    emit(NetworkResponseState.Success(mapCharacter(response.result)))
                }
                else -> {}
            }
        }.flowOn(ioDispatcher)
    }


    private fun mapCharacter(mapCharacter: Character?): CharacterDomainData {
        return characterUiDataMapper.map(mapCharacter)
    }



}