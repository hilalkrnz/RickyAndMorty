package com.example.rickyandmorty.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [HateCharacter::class], version = 1, exportSchema = false)
abstract class HateCharacterDatabase : RoomDatabase() {
    abstract fun getHateCharacterDao(): HateCharacterDao
}