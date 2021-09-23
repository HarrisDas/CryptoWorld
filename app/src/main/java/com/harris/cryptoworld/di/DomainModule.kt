package com.harris.cryptoworld.di

import android.content.Context
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCase
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCaseImpl
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCase
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCaseImpl
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import com.harris.cryptoworld.utils.NetworkUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Singleton
    @Provides
    fun provideGetAllCryptoUseCase(
        repository: ICryptoRepository,
        @ApplicationContext context: Context
    ) =
        GetAllCryptoUseCaseImpl(repository, NetworkUtils(context)) as GetAllCryptoUseCase

    @Singleton
    @Provides
    fun provideConvertCryptoUseCase(
        repository: ICryptoRepository,
        @ApplicationContext context: Context
    ) = ConvertCryptoUseCaseImpl(repository, NetworkUtils(context)) as ConvertCryptoUseCase

}