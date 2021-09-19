package com.harris.cryptoworld.domain.interactors

import com.harris.cryptoworld.domain.model.Crypto

interface GetAllCryptoUseCase {
    suspend operator fun invoke(): List<Crypto>
}