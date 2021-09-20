package com.harris.cryptoworld.domain.interactors

import android.accounts.NetworkErrorException
import com.harris.cryptoworld.domain.model.Crypto

interface GetAllCryptoUseCase {

    @Throws(NetworkErrorException::class)
    suspend operator fun invoke(): List<Crypto>
}