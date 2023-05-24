package com.example.rickyandmorty.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LoveCharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToLove(loveCharacter: LoveCharacter)

    @Query("SELECT * FROM love_character")
    fun getLoveCharacters(): List<LoveCharacter>

    @Query("SELECT count(*) FROM love_character WHERE character_id = :id")
    suspend fun checkLoveCharacter(id: String): Int

    @Query("DELETE FROM love_character WHERE character_id = :id")
    suspend fun removeFromLove(id: String): Int
}