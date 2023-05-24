package com.example.rickyandmorty.data.source.local

import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.data.database.HateCharacterDao
import javax.inject.Inject

class LocalHateDataSourceImpl @Inject constructor(
    private val hateCharacterDao: HateCharacterDao
) : LocalHateDataSource {
    override suspend fun addToHate(hateCharacter: HateCharacter) =
        hateCharacterDao.addToHate(hateCharacter)

    override fun getHateCharacters(): NetworkResponseState<List<HateCharacter>> =
        try {
            val response = hateCharacterDao.getHateCharacters()
            NetworkResponseState.Success(response)
        } catch (e: Exception) {
            NetworkResponseState.Failure(e)
        }

    override suspend fun checkHateCharacter(id: String) =
        hateCharacterDao.checkHateCharacter(id)

    override suspend fun removeFromHate(id: String) =
        hateCharacterDao.removeFromHate(id)
}