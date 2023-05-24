package com.example.rickyandmorty.domain.repository

import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.LoveCharacter
import kotlinx.coroutines.flow.Flow

interface LoveCharacterRepository {
    suspend fun addToLove(loveCharacter: LoveCharacter)

    fun getLoveCharacters(): Flow<NetworkResponseState<List<LoveCharacter>>>

    suspend fun checkLoveCharacter(id: String): Int

    suspend fun removeFromLove(id: String): Int
}