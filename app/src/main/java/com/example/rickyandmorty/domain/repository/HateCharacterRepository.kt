package com.example.rickyandmorty.domain.repository

import com.example.rickyandmorty.data.database.HateCharacter

interface HateCharacterRepository {
    suspend fun addToHate(hateCharacter: HateCharacter)
    fun getHateCharacters(): List<HateCharacter>
    suspend fun checkHateCharacter(id: String): Int
    suspend fun removeFromHate(id: String): Int

}