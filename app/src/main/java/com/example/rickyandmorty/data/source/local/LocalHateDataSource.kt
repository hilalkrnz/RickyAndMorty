package com.example.rickyandmorty.data.source.local

import com.example.rickyandmorty.data.NetworkResponseState
import com.example.rickyandmorty.data.database.HateCharacter

interface LocalHateDataSource {

    suspend fun addToHate(hateCharacter: HateCharacter)
    fun getHateCharacters(): NetworkResponseState<List<HateCharacter>>
    suspend fun checkHateCharacter(id: String): Int
    suspend fun removeFromHate(id: String): Int
}