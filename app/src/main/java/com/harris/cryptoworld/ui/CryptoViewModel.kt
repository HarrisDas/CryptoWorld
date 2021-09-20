package com.harris.cryptoworld.ui

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCase
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCase
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.domain.model.CryptoConvert
import com.harris.cryptoworld.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(
    private val getAllCryptoUseCase: GetAllCryptoUseCase,
    private val convertCryptoUseCase: ConvertCryptoUseCase
) :
    ViewModel() {
    val cryptoList = MutableLiveData<UIState<List<Crypto>>>()
    val cryptoConvert = MutableLiveData<UIState<CryptoConvert>>()

    init {
        getAllCrypto()
    }

    private fun getAllCrypto() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cryptoList.postValue(UIState.LoadingState)
                try {
                    cryptoList.postValue(UIState.DataState(getAllCryptoUseCase()))
                } catch (e: NetworkErrorException) {
                    Timber.e(e)
                    cryptoList.postValue(Utils.resolveError(e))

                }
            }

        }
    }

    private fun convertCrypto(from: String, to: String, amount: Double) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cryptoConvert.postValue(UIState.LoadingState)
                try {
                    cryptoConvert.postValue(
                        UIState.DataState(
                            convertCryptoUseCase(
                                from,
                                to,
                                amount
                            )
                        )
                    )
                } catch (e: NetworkErrorException) {
                    Timber.e(e)
                    cryptoConvert.postValue(Utils.resolveError(e))

                }
            }

        }
    }

}