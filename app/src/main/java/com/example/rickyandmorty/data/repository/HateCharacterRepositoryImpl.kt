package com.example.rickyandmorty.data.repository

import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.data.source.local.LocalHateDataSource
import com.example.rickyandmorty.di.coroutine.IoDispatcher
import com.example.rickyandmorty.domain.repository.HateCharacterRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HateCharacterRepositoryImpl @Inject constructor(
    private val localHateDataSource: LocalHateDataSource,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : HateCharacterRepository {
    override suspend fun addToHate(hateCharacter: HateCharacter) =
        withContext(ioDispatcher) {
            localHateDataSource.addToHate(hateCharacter)
        }

    override fun getHateCharacters(): Flow<NetworkResponseState<List<HateCharacter>>> =
        flow {
            emit(NetworkResponseState.Loading)
            when (val response = localHateDataSource.getHateCharacters()) {
                is NetworkResponseState.Failure -> emit(NetworkResponseState.Failure(response.exception))
                is NetworkResponseState.Success -> emit(NetworkResponseState.Success(response.result))
                else -> {}
            }
        }.flowOn(ioDispatcher)


    override suspend fun checkHateCharacter(id: String) =
        withContext(ioDispatcher) {
            localHateDataSource.checkHateCharacter(id)
        }

    override suspend fun removeFromHate(id: String) =
        withContext(ioDispatcher) {
            localHateDataSource.removeFromHate(id)
        }

}