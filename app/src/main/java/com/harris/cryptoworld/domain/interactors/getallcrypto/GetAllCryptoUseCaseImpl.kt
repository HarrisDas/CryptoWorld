package com.harris.cryptoworld.domain.interactors.getallcrypto

import android.accounts.NetworkErrorException
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import javax.inject.Inject

class GetAllCryptoUseCaseImpl @Inject constructor(
    private val repository: ICryptoRepository
) :
    GetAllCryptoUseCase {
    @Throws(NetworkErrorException::class)
    override suspend fun invoke(): List<Crypto> {

        val cryptoListResponse = repository.getAllCrypto()
        if (cryptoListResponse.success == true && cryptoListResponse.crypto != null) {
            return cryptoListResponse.crypto.map {
                it.value.toBean()
            }
        }
        throw NetworkErrorException(cryptoListResponse.error?.info ?: "unable to fetch data")
    }
}