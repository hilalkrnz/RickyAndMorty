package com.example.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.model.entity.HateCharacterEntity

@Dao
interface HateCharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToHate(hateCharacter: HateCharacterEntity)

    @Query("SELECT * FROM hate_character")
    fun getHateCharacters(): List<HateCharacterEntity>

    @Query("SELECT count(*) FROM hate_character WHERE character_id = :id")
    suspend fun checkHateCharacter(id: String): Int

    @Query("DELETE FROM hate_character WHERE character_id = :id")
    suspend fun removeFromHate(id: String): Int

}