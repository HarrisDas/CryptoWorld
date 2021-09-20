package com.harris.cryptoworld.ui

import android.accounts.NetworkErrorException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.harris.cryptoworld.domain.UIState
import com.harris.cryptoworld.domain.interactors.GetAllCryptoUseCase
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(private val getAllCryptoUseCase: GetAllCryptoUseCase) :
    ViewModel() {
    val mliveData = MutableLiveData<UIState<List<Crypto>>>()

    init {
        getAllCrypto()
    }

    private fun getAllCrypto() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                mliveData.postValue(UIState.LoadingState)
                try {
                    mliveData.postValue(UIState.DataState(getAllCryptoUseCase()))
                } catch (e: NetworkErrorException) {
                    Timber.e(e)
                    mliveData.postValue(Utils.resolveError(e))

                }
            }

        }
    }
}