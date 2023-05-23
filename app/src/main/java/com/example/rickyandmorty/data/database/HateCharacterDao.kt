package com.example.rickyandmorty.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HateCharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToHate(hateCharacter: HateCharacter)

    @Query("SELECT * FROM hate_character")
    fun getHateCharacters(): List<HateCharacter>

    @Query("SELECT count(*) FROM hate_character WHERE character_id = :id")
    suspend fun checkHateCharacter(id: String): Int

    @Query("DELETE FROM hate_character WHERE character_id = :id")
    suspend fun removeFromHate(id: String): Int

}