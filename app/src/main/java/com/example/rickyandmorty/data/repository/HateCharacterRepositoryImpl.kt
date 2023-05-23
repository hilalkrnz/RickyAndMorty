package com.example.rickyandmorty.data.repository

import com.example.rickyandmorty.data.database.HateCharacter
import com.example.rickyandmorty.data.database.HateCharacterDao
import com.example.rickyandmorty.domain.repository.HateCharacterRepository
import javax.inject.Inject

class HateCharacterRepositoryImpl @Inject constructor(
    private val hateCharacterDao: HateCharacterDao
) : HateCharacterRepository {
    override suspend fun addToHate(hateCharacter: HateCharacter) =
        hateCharacterDao.addToHate(hateCharacter)

    override fun getHateCharacters(): List<HateCharacter> =
        hateCharacterDao.getHateCharacters()

    override suspend fun checkHateCharacter(id: String) =
        hateCharacterDao.checkHateCharacter(id)

    override suspend fun removeFromHate(id: String) =
        hateCharacterDao.removeFromHate(id)

}