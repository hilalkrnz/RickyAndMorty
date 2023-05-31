package com.example.core.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.core.domain.model.entity.HateCharacterEntity
import com.example.core.domain.model.entity.LoveCharacterEntity

@Database(entities = [LoveCharacterEntity::class, HateCharacterEntity::class], version = 1, exportSchema = false)
abstract class StatusCharacterDatabase : RoomDatabase() {
    abstract fun getLoveCharacterDao(): LoveCharacterDao
    abstract fun getHateCharacterDao(): HateCharacterDao
}