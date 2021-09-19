package com.harris.cryptoworld.di

import com.harris.cryptoworld.data.datasource.ProductDataSource
import com.harris.cryptoworld.data.mapper.ProductNetworkMapper
import com.harris.cryptoworld.data.repository.ProductRepository
import com.harris.cryptoworld.data.service.ProductApiService
import com.harris.cryptoworld.domain.repository.IProductRepository
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
    fun provideProductDataSource(apiService: ProductApiService) =
        ProductDataSource(apiService)

    @Singleton
    @Provides
    fun provideProductRepository(dataSource: ProductDataSource, mapper: ProductNetworkMapper) =
        ProductRepository(dataSource, mapper) as IProductRepository


}