package com.example.rickyandmorty.di.mapper

import com.example.rickyandmorty.data.dto.Character
import com.example.rickyandmorty.data.mapper.CharacterUiDataMapperImpl
import com.example.rickyandmorty.data.mapper.Mapper
import com.example.rickyandmorty.domain.model.CharacterUiData
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindCharacterDomainMapper(
        characterUiDataMapperImpl: CharacterUiDataMapperImpl
    ): Mapper<Character, CharacterUiData>
}