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
    val currentCrypto = MutableLiveData<Crypto>()
    val enteredAmount = MutableLiveData(0.0)
    val convertedAmount = MutableLiveData(0.0)
    val conversionRate = MutableLiveData(0.0)

    fun selectCrypto(crypto: Crypto) {
        this.currentCrypto.postValue(crypto)
    }

    fun onAmountChange(enteredAmount: String, conversionRate: Double?) {
        val validate = enteredAmount.toDoubleOrNull()
        validate?.let {
            this@CryptoViewModel.enteredAmount.postValue(validate)
            convertedAmount.postValue(conversionRate?.times(validate))
        }
    }

    fun getAllCrypto() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                cryptoList.postValue(UIState.LoadingState)
                try {
                    cryptoList.postValue(getAllCryptoUseCase.invoke())
                } catch (e: NetworkErrorException) {
                    Timber.e(e)
                    cryptoList.postValue(Utils.resolveError(e))
                }
            }

        }
    }

    fun convertCrypto(from: String, to: String, amount: Double) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                conversionError.postValue(null)
                try {
                    val state = convertCryptoUseCase(
                        from,
                        to,
                        amount
                    )
                    when (state) {
                        is UIState.DataState -> {
                            val conversion = state.data
                            convertedAmount.postValue(conversion.amount)
                            conversionRate.postValue(conversion.rate)

                        }
                        is UIState.ErrorState -> {
                            conversionError.postValue(state)

                        }
                    }
                } catch (e: NetworkErrorException) {
                    Timber.e(e)
                    conversionError.postValue(Utils.resolveError(e))

                }
            }

        }
    }

    fun onCurrencySelected(currency: String) {
        convertCrypto(
            currentCrypto.value?.name ?: "",
            currency,
            enteredAmount.value?.toDouble() ?: 0.0
        )
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}