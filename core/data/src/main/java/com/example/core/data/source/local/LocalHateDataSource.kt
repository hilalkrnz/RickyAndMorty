package com.example.core.data.source.local

import com.example.core.common.NetworkResponseState
import com.example.core.domain.model.entity.HateCharacterEntity

interface LocalHateDataSource {
    suspend fun addToHate(hateCharacter: HateCharacterEntity)
    fun getHateCharacters(): NetworkResponseState<List<HateCharacterEntity>>
    suspend fun checkHateCharacter(id: String): Int
    suspend fun removeFromHate(id: String): Int
}