package com.harris.cryptoworld.data.datasource

import com.harris.cryptoworld.data.service.CryptoApiService
import javax.inject.Inject

class CryptoDataSource @Inject constructor(private val apiService: CryptoApiService) :
    BaseDataSource() {
    suspend fun getAllCrypto() = getResult {
        apiService.getAllCryptoCurrenciesAsync()
    }

    suspend fun convertCrypto(from: String, to: String, amount: Double) = getResult {
        apiService.convertCurrency(from, to, amount)
    }
}