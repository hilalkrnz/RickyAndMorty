package com.example.rickyandmorty.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [LoveCharacter::class], version = 1, exportSchema = false)
abstract class LoveCharacterDatabase : RoomDatabase() {
    abstract fun getLoveCharacterDao(): LoveCharacterDao
}