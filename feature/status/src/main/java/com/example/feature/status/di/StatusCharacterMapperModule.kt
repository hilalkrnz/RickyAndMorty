package com.example.feature.status.di

import com.example.core.common.mapper.ListMapper
import com.example.core.domain.model.entity.HateCharacterEntity
import com.example.core.domain.model.entity.LoveCharacterEntity
import com.example.core.ui.model.HateCharacter
import com.example.core.ui.model.LoveCharacter
import com.example.feature.status.hate.HateCharacterMapperImpl
import com.example.feature.status.love.LoveCharacterMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class StatusCharacterMapperModule {

    @Binds
    @Singleton
    abstract fun bindHateCharacterMapper(
        hateCharacterMapperImpl: HateCharacterMapperImpl
    ) : ListMapper<HateCharacterEntity, HateCharacter>

    @Binds
    @Singleton
    abstract fun bindLoveCharacterMapper(
        loveCharacterMapperImpl: LoveCharacterMapperImpl
    ) : ListMapper<LoveCharacterEntity, LoveCharacter>
}