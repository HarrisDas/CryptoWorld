package com.harris.cryptoworld.domain.interactors.convertcrypto

import android.accounts.NetworkErrorException
import com.harris.cryptoworld.domain.model.CryptoConvert
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import javax.inject.Inject

class ConvertCryptoUseCaseImpl @Inject constructor(private val repository: ICryptoRepository) :
    ConvertCryptoUseCase {
    override suspend fun invoke(from: String, to: String, amount: Double): CryptoConvert {
        val convertCrypto = repository.convertCrypto(from, to, amount)
        if (convertCrypto.success == true) {
            return convertCrypto.toBean()
        }
        throw NetworkErrorException("Unable to convert! ${convertCrypto.error?.info ?: ""}")
    }
}