package com.harris.cryptoworld.data.service

import com.harris.cryptoworld.data.remote.model.RemoteCrypto
import retrofit2.Response
import retrofit2.http.GET

interface CryptoApiService {

    @GET("/products")
    suspend fun getAllCryptoCurrenciesAsync(): Response<List<RemoteCrypto>>
}