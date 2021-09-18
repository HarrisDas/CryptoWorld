package com.harris.mygroceryshop.di

import com.harris.mygroceryshop.data.datasource.ProductDataSource
import com.harris.mygroceryshop.data.mapper.ProductNetworkMapper
import com.harris.mygroceryshop.data.repository.ProductRepository
import com.harris.mygroceryshop.data.service.ProductApiService
import com.harris.mygroceryshop.domain.repository.IProductRepository
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