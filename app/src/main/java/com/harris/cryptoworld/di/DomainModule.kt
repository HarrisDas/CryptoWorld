package com.harris.cryptoworld.di

import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCase
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCaseImpl
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCase
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCaseImpl
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

    @Singleton
    @Provides
    fun provideConvertCryptoUseCase(repository: ICryptoRepository) =
        ConvertCryptoUseCaseImpl(repository) as ConvertCryptoUseCase

}