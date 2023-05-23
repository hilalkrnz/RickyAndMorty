package com.example.rickyandmorty.di.repository

import com.example.rickyandmorty.data.repository.HateCharacterRepositoryImpl
import com.example.rickyandmorty.domain.repository.HateCharacterRepository
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