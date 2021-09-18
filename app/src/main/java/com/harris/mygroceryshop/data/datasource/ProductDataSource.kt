package com.harris.mygroceryshop.data.datasource

import com.harris.mygroceryshop.data.service.ProductApiService
import javax.inject.Inject

class ProductDataSource @Inject constructor(private val apiService: ProductApiService) :
    BaseDataSource() {
    suspend fun getAllProducts() = getResult {
        apiService.getAllProductsAsync()
    }
}