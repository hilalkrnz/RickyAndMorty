package com.example.core.data.source.local

import com.example.core.common.NetworkResponseState
import com.example.core.domain.model.entity.LoveCharacterEntity

interface LocalLoveDataSource {
    suspend fun addToLove(loveCharacter: LoveCharacterEntity)
    fun getLoveCharacters(): NetworkResponseState<List<LoveCharacterEntity>>
    suspend fun checkLoveCharacter(id: String): Int
    suspend fun removeFromLove(id: String): Int
}