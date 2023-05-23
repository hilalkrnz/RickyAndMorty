package com.example.rickyandmorty.data.source.local

import com.example.rickyandmorty.data.database.FavoriteCharacter
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    suspend fun addToFavorite(favoriteCharacter: FavoriteCharacter)
    fun getFavoriteCharacters(): Flow<List<FavoriteCharacter>>
    suspend fun checkFavoriteCharacter(id: String): Int
    suspend fun removeFromFavorite(id: String): Int
}