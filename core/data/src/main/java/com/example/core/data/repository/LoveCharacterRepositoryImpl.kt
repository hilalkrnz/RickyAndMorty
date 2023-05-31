package com.example.core.data.repository

import android.util.Log
import com.example.core.common.DataResponseState
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

    override fun getLoveCharacters(): Flow<DataResponseState<List<LoveCharacterEntity>>> =
        flow {
            emit(DataResponseState.Loading)
            when (val response = localLoveDataSource.getLoveCharacters()) {
                is DataResponseState.Failure -> emit(DataResponseState.Failure(response.exception))
                is DataResponseState.Success -> emit(DataResponseState.Success(response.result))
                DataResponseState.Loading -> Log.d("TAG", "Loading love characters response state")
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