package com.example.core.domain.repository

import com.example.core.common.DataResponseState
import com.example.core.domain.model.entity.LoveCharacterEntity
import kotlinx.coroutines.flow.Flow

interface LoveCharacterRepository {
    suspend fun addToLove(loveCharacter: LoveCharacterEntity)
    fun getLoveCharacters(): Flow<DataResponseState<List<LoveCharacterEntity>>>
    suspend fun checkLoveCharacter(id: String): Int
    suspend fun removeFromLove(id: String): Int
}