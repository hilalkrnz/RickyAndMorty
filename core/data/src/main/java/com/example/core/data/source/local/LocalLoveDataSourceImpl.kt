package com.example.core.data.source.local

import com.example.core.common.NetworkResponseState
import com.example.core.data.database.LoveCharacterDao
import com.example.core.domain.model.entity.LoveCharacterEntity
import javax.inject.Inject

class LocalLoveDataSourceImpl @Inject constructor(
    private val loveCharacterDao: LoveCharacterDao
) : LocalLoveDataSource {
    override suspend fun addToLove(loveCharacter: LoveCharacterEntity) =
        loveCharacterDao.addToLove(loveCharacter)

    override fun getLoveCharacters(): NetworkResponseState<List<LoveCharacterEntity>> =
        try {
            val response = loveCharacterDao.getLoveCharacters()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Failure(e)
        }

    override suspend fun checkLoveCharacter(id: String) =
        loveCharacterDao.checkLoveCharacter(id)

    override suspend fun removeFromLove(id: String) =
        loveCharacterDao.removeFromLove(id)
}