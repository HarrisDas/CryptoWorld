package com.harris.cryptoworld.domain.interactors.convertcrypto

import com.harris.cryptoworld.data.exception.NetworkErrorException
import com.harris.cryptoworld.domain.model.CryptoConvert
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import com.harris.cryptoworld.presentation.ui.UIState
import com.harris.cryptoworld.utils.NetworkUtils
import javax.inject.Inject

class ConvertCryptoUseCaseImpl @Inject constructor(
    private val repository: ICryptoRepository,
    private val networkUtils: NetworkUtils
) :
    ConvertCryptoUseCase {
    override suspend fun invoke(from: String, to: String, amount: Double): UIState<CryptoConvert> {
        return if (networkUtils.isInternetConnected()) {
            val convertCrypto = repository.convertCrypto(from, to, amount)
            if (convertCrypto.success == true) {
                UIState.DataState(convertCrypto.toBean())
            } else
                UIState.ErrorState(NetworkErrorException(errorMessage = "Unable to convert! ${convertCrypto.error?.info ?: ""}"))
        } else {
            UIState.ErrorState(NetworkErrorException(errorMessage = "No Internet!"))
        }

    }
}