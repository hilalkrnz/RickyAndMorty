package com.example.rickyandmorty.data.source.local

import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.LoveCharacter
import com.example.rickyandmorty.data.database.LoveCharacterDao
import javax.inject.Inject

class LocalLoveDataSourceImpl @Inject constructor(
    private val loveCharacterDao: LoveCharacterDao
) : LocalLoveDataSource {
    override suspend fun addToLove(loveCharacter: LoveCharacter) =
        loveCharacterDao.addToLove(loveCharacter)

    override fun getLoveCharacters(): NetworkResponseState<List<LoveCharacter>> =
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