package com.example.rickyandmorty.di.repository

import com.example.rickyandmorty.data.repository.LoveCharacterRepositoryImpl
import com.example.rickyandmorty.domain.repository.LoveCharacterRepository
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