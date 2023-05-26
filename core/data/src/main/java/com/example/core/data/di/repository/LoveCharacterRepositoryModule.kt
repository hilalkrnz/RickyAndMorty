package com.example.core.data.di.repository

import com.example.core.data.repository.LoveCharacterRepositoryImpl
import com.example.core.domain.repository.LoveCharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LoveCharacterRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindLoveCharacterRepository(
        loveCharacterRepositoryImpl: LoveCharacterRepositoryImpl
    ): LoveCharacterRepository
}