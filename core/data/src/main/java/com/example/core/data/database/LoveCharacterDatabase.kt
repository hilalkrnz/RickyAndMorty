package com.example.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.domain.model.entity.LoveCharacterEntity

@Database(entities = [LoveCharacterEntity::class], version = 1, exportSchema = false)
abstract class LoveCharacterDatabase : RoomDatabase() {
    abstract fun getLoveCharacterDao(): LoveCharacterDao
}