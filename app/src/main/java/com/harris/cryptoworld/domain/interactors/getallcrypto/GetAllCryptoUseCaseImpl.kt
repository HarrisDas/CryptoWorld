package com.harris.cryptoworld.domain.interactors.getallcrypto

import android.accounts.NetworkErrorException
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import com.harris.cryptoworld.presentation.ui.UIState
import javax.inject.Inject

class GetAllCryptoUseCaseImpl @Inject constructor(
    private val repository: ICryptoRepository
) :
    GetAllCryptoUseCase {
    override suspend fun invoke(): UIState<List<Crypto>> {

        val cryptoListResponse = repository.getAllCrypto()
        if (cryptoListResponse.success == true && cryptoListResponse.crypto != null) {
            return UIState.DataState(cryptoListResponse.crypto.map {
                it.value.toBean()
            })
        }
        return UIState.ErrorState(
            NetworkErrorException(
                cryptoListResponse.error?.info ?: "unable to fetch data"
            )
        )
    }
}