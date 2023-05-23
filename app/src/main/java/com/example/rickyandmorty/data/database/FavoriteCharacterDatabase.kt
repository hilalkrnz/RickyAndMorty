package com.example.rickyandmorty.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [FavoriteCharacter::class], version = 1, exportSchema = false)
abstract class FavoriteCharacterDatabase : RoomDatabase() {
    abstract fun getFavoriteCharacterDao(): FavoriteCharacterDao
}