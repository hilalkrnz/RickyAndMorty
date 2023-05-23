package com.example.rickyandmorty.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteCharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToFavorite(favoriteCharacter: FavoriteCharacter)

    @Query("SELECT * FROM favorite_character")
    fun getFavoriteCharacters(): List<FavoriteCharacter>

    @Query("SELECT count(*) FROM favorite_character WHERE character_id = :id")
    suspend fun checkFavoriteCharacter(id: String): Int

    @Query("DELETE FROM favorite_character WHERE character_id = :id")
    suspend fun removeFromFavorite(id: String): Int
}