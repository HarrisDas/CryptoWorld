package com.harris.cryptoworld.domain.repository

import com.harris.cryptoworld.domain.model.Crypto

interface ICryptoRepository {

    suspend fun getAllCrypto(): List<Crypto>
}