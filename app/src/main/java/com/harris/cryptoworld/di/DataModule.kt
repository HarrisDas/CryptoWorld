package com.harris.cryptoworld.di

import com.harris.cryptoworld.data.datasource.CryptoDataSource
import com.harris.cryptoworld.data.mapper.CryptoNetworkMapper
import com.harris.cryptoworld.data.repository.CryptoRepository
import com.harris.cryptoworld.data.service.CryptoApiService
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataModule {
    @Singleton
    @Provides
    fun provideCryptoDataSource(apiService: CryptoApiService) =
        CryptoDataSource(apiService)

    @Singleton
    @Provides
    fun provideCryptoRepository(dataSource: CryptoDataSource, mapper: CryptoNetworkMapper) =
        CryptoRepository(dataSource, mapper) as ICryptoRepository


}