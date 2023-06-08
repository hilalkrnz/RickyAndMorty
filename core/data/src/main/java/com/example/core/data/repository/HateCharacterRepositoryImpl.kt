package com.example.core.data.repository

import android.util.Log
import com.example.core.common.DataResponseState
import com.example.core.common.coroutine.IoDispatcher
import com.example.core.data.source.local.LocalHateDataSource
import com.example.core.domain.model.entity.HateCharacterEntity
import com.example.core.domain.repository.HateCharacterRepository
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
    override suspend fun addToHate(hateCharacter: HateCharacterEntity) =
        withContext(ioDispatcher) {
            localHateDataSource.addToHate(hateCharacter)
        }

    override fun getHateCharacters(): Flow<DataResponseState<List<HateCharacterEntity>>> =
        flow {
            emit(DataResponseState.Loading)
            when (val response = localHateDataSource.getHateCharacters()) {
                is DataResponseState.Failure -> emit(DataResponseState.Failure(response.exception))
                is DataResponseState.Success -> emit(DataResponseState.Success(response.result))
                DataResponseState.Loading -> Log.d("TAG", "Loading hate characters response state")
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