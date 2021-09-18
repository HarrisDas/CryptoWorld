package com.harris.mygroceryshop.data.service

import com.harris.mygroceryshop.data.remote.model.RemoteProduct
import retrofit2.Response
import retrofit2.http.GET

interface ProductApiService {

    @GET("/products")
    suspend fun getAllProductsAsync(): Response<List<RemoteProduct>>
}