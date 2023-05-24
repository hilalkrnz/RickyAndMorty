package com.example.rickyandmorty.domain.repository

import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.HateCharacter
import kotlinx.coroutines.flow.Flow

interface HateCharacterRepository {
    suspend fun addToHate(hateCharacter: HateCharacter)
    fun getHateCharacters(): Flow<NetworkResponseState<List<HateCharacter>>>
    suspend fun checkHateCharacter(id: String): Int
    suspend fun removeFromHate(id: String): Int

}