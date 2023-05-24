package com.example.rickyandmorty.data.source.local

import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.LoveCharacter

interface LocalLoveDataSource {
    suspend fun addToLove(loveCharacter: LoveCharacter)
    fun getLoveCharacters(): NetworkResponseState<List<LoveCharacter>>
    suspend fun checkLoveCharacter(id: String): Int
    suspend fun removeFromLove(id: String): Int
}