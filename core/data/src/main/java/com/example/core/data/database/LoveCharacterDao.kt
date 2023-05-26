package com.example.core.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.core.domain.model.entity.LoveCharacterEntity

@Dao
interface LoveCharacterDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addToLove(loveCharacter: LoveCharacterEntity)

    @Query("SELECT * FROM love_character")
    fun getLoveCharacters(): List<LoveCharacterEntity>

    @Query("SELECT count(*) FROM love_character WHERE character_id = :id")
    suspend fun checkLoveCharacter(id: String): Int

    @Query("DELETE FROM love_character WHERE character_id = :id")
    suspend fun removeFromLove(id: String): Int
}