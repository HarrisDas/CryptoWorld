package com.harris.cryptoworld.domain.interactors.convertcrypto

import com.harris.cryptoworld.domain.model.CryptoConvert
import com.harris.cryptoworld.presentation.ui.UIState
import javax.inject.Inject
import kotlin.random.Random

class MockConvertCryptoUseCaseImpl @Inject constructor() :
    ConvertCryptoUseCase {
    override suspend fun invoke(from: String, to: String, amount: Double): UIState<CryptoConvert> {
        val randomRate = Random.Default.nextDouble(1.0, 100.0)
        return UIState.DataState(CryptoConvert(randomRate * amount, randomRate))
    }
}