package com.example.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.domain.model.entity.HateCharacterEntity

@Database(entities = [HateCharacterEntity::class], version = 1, exportSchema = false)
abstract class HateCharacterDatabase : RoomDatabase() {
    abstract fun getHateCharacterDao(): HateCharacterDao
}