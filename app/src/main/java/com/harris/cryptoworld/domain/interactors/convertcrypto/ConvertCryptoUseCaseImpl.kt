package com.harris.cryptoworld.domain.interactors.convertcrypto

import com.harris.cryptoworld.domain.repository.ICryptoRepository
import javax.inject.Inject

class ConvertCryptoUseCaseImpl @Inject constructor(private val repository: ICryptoRepository) :
    ConvertCryptoUseCase {
    override fun invoke(from: String, to: String, amount: Double) {
    }
}