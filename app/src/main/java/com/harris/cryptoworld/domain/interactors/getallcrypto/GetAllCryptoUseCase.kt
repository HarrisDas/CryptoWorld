package com.harris.cryptoworld.domain.interactors.getallcrypto

import android.accounts.NetworkErrorException
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.presentation.ui.UIState

interface GetAllCryptoUseCase {

    @Throws(NetworkErrorException::class)
    suspend operator fun invoke(): UIState<List<Crypto>>
}