package com.example.core.data.di.repository

import com.example.core.data.repository.RickAndMortyRepositoryImpl
import com.example.core.domain.repository.RickAndMortyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class RickAndMortyRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindRickAndMortyRepository(
        rickAndMortyRepositoryImpl: RickAndMortyRepositoryImpl
    ): RickAndMortyRepository
}