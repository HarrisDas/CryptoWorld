package com.harris.cryptoworld.domain.interactors.convertcrypto

import com.harris.cryptoworld.domain.model.CryptoConvert
import com.harris.cryptoworld.presentation.ui.UIState

interface ConvertCryptoUseCase {
    suspend operator fun invoke(from: String, to: String, amount: Double): UIState<CryptoConvert>

}