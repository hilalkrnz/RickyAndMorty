package com.example.core.data.source.local

import com.example.core.common.DataResponseState
import com.example.core.domain.model.entity.HateCharacterEntity

interface LocalHateDataSource {
    suspend fun addToHate(hateCharacter: HateCharacterEntity)
    fun getHateCharacters(): DataResponseState<List<HateCharacterEntity>>
    suspend fun checkHateCharacter(id: String): Int
    suspend fun removeFromHate(id: String): Int
}