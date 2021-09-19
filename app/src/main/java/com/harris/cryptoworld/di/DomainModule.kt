package com.harris.cryptoworld.di

import com.harris.cryptoworld.domain.interactors.GetAllCryptoUseCase
import com.harris.cryptoworld.domain.interactors.GetAllCryptoUseCaseImpl
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideGetAllCryptoUseCase(repository: ICryptoRepository) =
        GetAllCryptoUseCaseImpl(repository) as GetAllCryptoUseCase
}