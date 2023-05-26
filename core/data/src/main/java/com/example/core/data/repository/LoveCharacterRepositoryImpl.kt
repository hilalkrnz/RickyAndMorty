package com.example.core.data.repository

import com.example.core.common.NetworkResponseState
import com.example.core.common.coroutine.IoDispatcher
import com.example.core.data.source.local.LocalLoveDataSource
import com.example.core.domain.model.entity.LoveCharacterEntity
import com.example.core.domain.repository.LoveCharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoveCharacterRepositoryImpl @Inject constructor(
    private val localLoveDataSource: LocalLoveDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : LoveCharacterRepository {
    override suspend fun addToLove(loveCharacter: LoveCharacterEntity) =
        withContext(ioDispatcher) {
            localLoveDataSource.addToLove(loveCharacter)
        }

    override fun getLoveCharacters(): Flow<NetworkResponseState<List<LoveCharacterEntity>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when (val response = localLoveDataSource.getLoveCharacters()) {
                is NetworkResponseState.Failure -> emit(NetworkResponseState.Failure(response.exception))
                is NetworkResponseState.Success -> emit(NetworkResponseState.Success(response.result))
                else -> {}
            }
        }.flowOn(ioDispatcher)

    override suspend fun checkLoveCharacter(id: String) =
        withContext(ioDispatcher) {
            localLoveDataSource.checkLoveCharacter(id)
        }


    override suspend fun removeFromLove(id: String) =
        withContext(ioDispatcher) {
            localLoveDataSource.removeFromLove(id)
        }

}