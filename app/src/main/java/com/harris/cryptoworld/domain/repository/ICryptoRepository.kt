package com.harris.cryptoworld.domain.repository

import com.harris.cryptoworld.data.remote.model.CryptoListResponse

interface ICryptoRepository {

    suspend fun getAllCrypto(): CryptoListResponse
}