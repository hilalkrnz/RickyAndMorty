package com.example.rickyandmorty.di.database

import android.content.Context
import androidx.room.Room
import com.example.rickyandmorty.data.database.FavoriteCharacterDatabase
import com.example.rickyandmorty.data.database.HateCharacterDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideFavoriteCharacterDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        FavoriteCharacterDatabase::class.java,
        "favorite_character_database"
    ).build()

    @Provides
    @Singleton
    fun providesFavoriteCharacterDb(database: FavoriteCharacterDatabase) = database.getFavoriteCharacterDao()


    @Provides
    @Singleton
    fun provideHateCharacterDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        HateCharacterDatabase::class.java,
        "hate_character_database"
    ).build()

    @Provides
    @Singleton
    fun providesHateCharacterDb(database: HateCharacterDatabase) = database.getHateCharacterDao()
}