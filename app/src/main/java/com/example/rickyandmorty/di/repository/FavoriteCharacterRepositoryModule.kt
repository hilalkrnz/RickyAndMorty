package com.example.rickyandmorty.di.repository

import com.example.rickyandmorty.data.repository.FavoriteCharacterRepositoryImpl
import com.example.rickyandmorty.data.repository.RickAndMortyRepositoryImpl
import com.example.rickyandmorty.domain.repository.FavoriteCharacterRepository
import com.example.rickyandmorty.domain.repository.RickAndMortyRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteCharacterRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindFavoriteCharacterRepository(
        favoriteCharacterRepositoryImpl: FavoriteCharacterRepositoryImpl
    ): FavoriteCharacterRepository
}