package com.harris.cryptoworld.domain.repository

import com.harris.cryptoworld.data.remote.model.CryptoConvertResponse
import com.harris.cryptoworld.data.remote.model.CryptoListResponse

interface ICryptoRepository {

    suspend fun getAllCrypto(): CryptoListResponse
    suspend fun convertCrypto(from: String, to: String, amount: Double): CryptoConvertResponse
}