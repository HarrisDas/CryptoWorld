package com.harris.cryptoworld.data.service

import com.harris.cryptoworld.BuildConfig
import com.harris.cryptoworld.data.remote.model.CryptoListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CryptoApiService {

    @GET("/list")
    suspend fun getAllCryptoCurrenciesAsync(
        @Query("access_key") access_key: String = BuildConfig.API_KEY
    ): Response<CryptoListResponse>
}