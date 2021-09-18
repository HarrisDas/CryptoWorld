package com.harris.mygroceryshop.di

import com.google.gson.Gson
import com.harris.mygroceryshop.data.service.ProductApiService
import com.harris.mygroceryshop.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(Gson())
    }

    @Singleton
    @Provides
    fun provideOkHTTPClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor())
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(
        factory: GsonConverterFactory,
        client: OkHttpClient
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .client(client)
            .addConverterFactory(factory)
            .baseUrl(Constants.BASE_URL)

    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit.Builder): ProductApiService {
        return retrofit
            .build()
            .create(ProductApiService::class.java)
    }
}