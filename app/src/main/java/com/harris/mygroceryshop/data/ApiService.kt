package com.harris.mygroceryshop.data

import com.harris.mygroceryshop.data.remote.model.RemoteProduct
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("posts")
    fun getAllProductsAsync(): Deferred<List<RemoteProduct>>
}