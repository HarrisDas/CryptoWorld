package com.harris.cryptoworld.ui

import androidx.lifecycle.ViewModel
import com.harris.cryptoworld.domain.repository.IProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(private val repository: IProductRepository) :
    ViewModel() {

}