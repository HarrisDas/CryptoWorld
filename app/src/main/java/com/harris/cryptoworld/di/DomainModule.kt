package com.harris.cryptoworld.di

import android.content.Context
import com.harris.cryptoworld.BuildConfig
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCaseImpl
import com.harris.cryptoworld.domain.interactors.convertcrypto.MockConvertCryptoUseCaseImpl
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCaseImpl
import com.harris.cryptoworld.domain.interactors.getallcrypto.MockGetAllCryptoUseCaseImpl
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
        (if (BuildConfig.Mock) MockGetAllCryptoUseCaseImpl()
        else GetAllCryptoUseCaseImpl(repository, NetworkUtils(context)))

    @Singleton
    @Provides
    fun provideConvertCryptoUseCase(
        repository: ICryptoRepository,
        @ApplicationContext context: Context
    ) = (if (BuildConfig.Mock) MockConvertCryptoUseCaseImpl()
    else ConvertCryptoUseCaseImpl(
        repository,
        NetworkUtils(context)
    ))


}