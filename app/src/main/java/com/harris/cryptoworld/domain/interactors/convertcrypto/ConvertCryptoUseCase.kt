package com.harris.cryptoworld.domain.interactors.convertcrypto

import com.harris.cryptoworld.domain.model.CryptoConvert

interface ConvertCryptoUseCase {
    operator fun invoke(from: String, to: String, amount: Double): CryptoConvert

}