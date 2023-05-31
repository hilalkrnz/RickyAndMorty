package com.example.core.domain.repository

import com.example.core.common.DataResponseState
import com.example.core.domain.model.entity.HateCharacterEntity
import kotlinx.coroutines.flow.Flow

interface HateCharacterRepository {
    suspend fun addToHate(hateCharacter: HateCharacterEntity)
    fun getHateCharacters(): Flow<DataResponseState<List<HateCharacterEntity>>>
    suspend fun checkHateCharacter(id: String): Int
    suspend fun removeFromHate(id: String): Int

}