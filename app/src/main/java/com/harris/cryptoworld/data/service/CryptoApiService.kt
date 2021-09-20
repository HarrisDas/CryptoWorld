package com.harris.cryptoworld.data.service

import com.harris.cryptoworld.BuildConfig
import com.harris.cryptoworld.data.remote.model.CryptoConvertResponse
import com.harris.cryptoworld.data.remote.model.CryptoListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiService {

    @GET("/list")
    suspend fun getAllCryptoCurrenciesAsync(
        @Query("access_key") access_key: String = BuildConfig.API_KEY
    ): Response<CryptoListResponse>

    @GET("/convert")
    suspend fun convertCurrency(
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double,
        @Query("access_key") access_key: String = BuildConfig.API_KEY
    ): Response<CryptoConvertResponse>
}