package com.harris.cryptoworld.data.repository

import com.harris.cryptoworld.data.datasource.CryptoDataSource
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val dataSource: CryptoDataSource
) :
    ICryptoRepository {
    override suspend fun getAllCrypto() = dataSource.getAllCrypto()

}