package com.example.core.data.source.local

import com.example.core.common.DataResponseState
import com.example.core.data.database.HateCharacterDao
import com.example.core.domain.model.entity.HateCharacterEntity
import javax.inject.Inject

class LocalHateDataSourceImpl @Inject constructor(
    private val hateCharacterDao: HateCharacterDao
) : LocalHateDataSource {
    override suspend fun addToHate(hateCharacter: HateCharacterEntity) =
        hateCharacterDao.addToHate(hateCharacter)

    override fun getHateCharacters(): DataResponseState<List<HateCharacterEntity>> =
        try {
            val response = hateCharacterDao.getHateCharacters()
            DataResponseState.Success(response)
        } catch (e: Exception) {
            DataResponseState.Failure(e)
        }

    override suspend fun checkHateCharacter(id: String) =
        hateCharacterDao.checkHateCharacter(id)

    override suspend fun removeFromHate(id: String) =
        hateCharacterDao.removeFromHate(id)
}