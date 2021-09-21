package com.harris.cryptoworld.presentation

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCase
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCase
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.presentation.ui.UIState
import com.harris.cryptoworld.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
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
    val conversionError = MutableLiveData<UIState.ErrorState?>()
    val crypto = MutableLiveData<Crypto>()
    val convertedAmount = MutableLiveData(0.0)
    val enteredAmount = MutableLiveData(0.0)
    val conversionRate = MutableLiveData(0.0)

    init {
        getAllCrypto()
    }

    fun selectCrypto(crypto: Crypto) {
        this.crypto.postValue(crypto)
    }

    fun onAmountChange(enteredAmount: String) {
        this.enteredAmount.postValue(enteredAmount.toDouble())
        convertedAmount.postValue(conversionRate.value?.times(enteredAmount.toDouble()))
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
                conversionError.postValue(null)
                try {
                    val conversion = convertCryptoUseCase(
                        from,
                        to,
                        amount
                    )
                    convertedAmount.postValue(conversion.amount)
                    conversionRate.postValue(conversion.rate)
                } catch (e: NetworkErrorException) {
                    Timber.e(e)
                    conversionError.postValue(Utils.resolveError(e))

                }
            }

        }
    }

    fun onCurrencySelected(currency: String) {
        convertCrypto(
            crypto.value?.name ?: "",
            currency,
            enteredAmount.value?.toDouble() ?: 0.0
        )
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}