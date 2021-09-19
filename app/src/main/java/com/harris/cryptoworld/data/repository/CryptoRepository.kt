package com.harris.cryptoworld.data.repository

import com.harris.cryptoworld.data.datasource.CryptoDataSource
import com.harris.cryptoworld.data.mapper.CryptoNetworkMapper
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val dataSource: CryptoDataSource,
    private val mapper: CryptoNetworkMapper
) :
    ICryptoRepository {
    override suspend fun getAllCrypto(): List<Crypto> {
        val allCrypto = dataSource.getAllCrypto()
        return mapper.mapFromEntityList(allCrypto.body)
    }

}