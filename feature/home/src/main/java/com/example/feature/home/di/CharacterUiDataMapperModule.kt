package com.example.feature.home.di

import com.example.core.common.mapper.Mapper
import com.example.core.domain.model.CharacterDomainData
import com.example.core.ui.CharacterUiData
import com.example.feature.home.CharacterUiDataMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CharacterUiDataMapperModule {

    @Binds
    @Singleton
    abstract fun bindCharacterUiDataMapper(
        characterUiDataMapperImpl: CharacterUiDataMapperImpl
    ) : Mapper<CharacterDomainData, CharacterUiData>
}