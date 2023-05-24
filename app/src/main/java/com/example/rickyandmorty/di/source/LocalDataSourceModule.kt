package com.example.rickyandmorty.di.source

import com.example.rickyandmorty.data.source.local.LocalLoveDataSource
import com.example.rickyandmorty.data.source.local.LocalLoveDataSourceImpl
import com.example.rickyandmorty.data.source.local.LocalHateDataSource
import com.example.rickyandmorty.data.source.local.LocalHateDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalLoveDataSource(
        localLoveDataSourceImpl: LocalLoveDataSourceImpl
    ): LocalLoveDataSource

    @Binds
    @Singleton
    abstract fun bindLocalHateDataSource(
        localHateDataSourceImpl: LocalHateDataSourceImpl
    ): LocalHateDataSource
}