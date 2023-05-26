package com.example.core.data.di.mapper

import com.example.core.common.mapper.Mapper
import com.example.core.data.dto.Character
import com.example.core.data.mapper.CharacterDomainDataMapperImpl
import com.example.core.domain.model.CharacterDomainData
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
        characterDomainDataMapperImpl: CharacterDomainDataMapperImpl
    ): Mapper<Character, CharacterDomainData>

}