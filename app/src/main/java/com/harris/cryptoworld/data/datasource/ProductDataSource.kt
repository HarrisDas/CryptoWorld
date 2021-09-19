package com.harris.cryptoworld.data.datasource

import com.harris.cryptoworld.data.service.ProductApiService
import javax.inject.Inject

class ProductDataSource @Inject constructor(private val apiService: ProductApiService) :
    BaseDataSource() {
    suspend fun getAllProducts() = getResult {
        apiService.getAllProductsAsync()
    }
}