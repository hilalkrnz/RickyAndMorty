package com.example.core.data.di.database

import android.content.Context
import androidx.room.Room
import com.example.core.data.database.StatusCharacterDatabase
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
    fun provideLoveCharacterDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app,
        StatusCharacterDatabase::class.java,
        "status_character_database"
    ).build()

    @Provides
    @Singleton
    fun providesLoveCharacterDb(database: StatusCharacterDatabase) = database.getLoveCharacterDao()

    @Provides
    @Singleton
    fun providesHateCharacterDb(database: StatusCharacterDatabase) = database.getHateCharacterDao()
}