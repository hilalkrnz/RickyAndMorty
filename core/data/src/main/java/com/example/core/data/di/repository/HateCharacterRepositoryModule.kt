package com.example.core.data.di.repository

import com.example.core.data.repository.HateCharacterRepositoryImpl
import com.example.core.domain.repository.HateCharacterRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HateCharacterRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindHateCharacterRepository(
        hateCharacterRepositoryImpl: HateCharacterRepositoryImpl
    ): HateCharacterRepository
}