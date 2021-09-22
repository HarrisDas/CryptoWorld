package com.harris.cryptoworld.domain.interactors.convertcrypto

import com.harris.cryptoworld.data.exception.NetworkErrorException
import com.harris.cryptoworld.domain.model.CryptoConvert
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import com.harris.cryptoworld.presentation.ui.UIState
import javax.inject.Inject

class ConvertCryptoUseCaseImpl @Inject constructor(private val repository: ICryptoRepository) :
    ConvertCryptoUseCase {
    override suspend fun invoke(from: String, to: String, amount: Double): UIState<CryptoConvert> {
        val convertCrypto = repository.convertCrypto(from, to, amount)
        return if (convertCrypto.success == true) {
            UIState.DataState(convertCrypto.toBean())
        } else UIState.ErrorState(NetworkErrorException(errorMessage = "Unable to convert! ${convertCrypto.error?.info ?: ""}"))
    }
}