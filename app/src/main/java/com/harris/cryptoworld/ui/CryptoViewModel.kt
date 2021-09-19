package com.harris.cryptoworld.ui

import androidx.lifecycle.ViewModel
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CryptoViewModel @Inject constructor(private val repository: ICryptoRepository) :
    ViewModel() {

}