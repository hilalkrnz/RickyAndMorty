package com.example.rickyandmorty.domain.repository

import com.example.rickyandmorty.data.database.FavoriteCharacter
import kotlinx.coroutines.flow.Flow

interface FavoriteCharacterRepository {
    suspend fun addToFavorite(favoriteCharacter: FavoriteCharacter)

    fun getFavoriteCharacters(): List<FavoriteCharacter>

    suspend fun checkFavoriteCharacter(id: String): Int

    suspend fun removeFromFavorite(id: String): Int
}