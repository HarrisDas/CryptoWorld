package com.harris.cryptoworld.domain.interactors

import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import javax.inject.Inject

class GetAllCryptoUseCaseImpl @Inject constructor(private val repository: ICryptoRepository) :
    GetAllCryptoUseCase {
    override suspend fun invoke(): List<Crypto> {
        return repository.getAllCrypto()
    }
}