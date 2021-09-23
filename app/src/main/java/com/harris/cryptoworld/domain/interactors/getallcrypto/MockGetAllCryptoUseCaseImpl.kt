package com.harris.cryptoworld.domain.interactors.getallcrypto

import com.google.gson.Gson
import com.harris.cryptoworld.data.remote.model.CryptoListResponse
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.presentation.ui.UIState
import cryptoListResponse
import javax.inject.Inject

class MockGetAllCryptoUseCaseImpl @Inject constructor() :
    GetAllCryptoUseCase {
    override suspend fun invoke(): UIState<List<Crypto>> {
        return UIState.DataState(
            Gson().fromJson(
                cryptoListResponse,
                CryptoListResponse::class.java
            ).crypto?.map {
                it.value.toBean()
            } ?: emptyList()
        )


    }
}