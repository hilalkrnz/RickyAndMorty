package com.example.rickyandmorty.data.repository

import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.LoveCharacter
import com.example.rickyandmorty.data.source.local.LocalLoveDataSource
import com.example.rickyandmorty.di.coroutine.IoDispatcher
import com.example.rickyandmorty.domain.repository.LoveCharacterRepository
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
    override suspend fun addToLove(loveCharacter: LoveCharacter) =
        withContext(ioDispatcher) {
            localLoveDataSource.addToLove(loveCharacter)
        }

    override fun getLoveCharacters(): Flow<NetworkResponseState<List<LoveCharacter>>> =
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