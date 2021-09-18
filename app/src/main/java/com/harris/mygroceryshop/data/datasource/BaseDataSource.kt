package com.harris.mygroceryshop.data.datasource

import com.harris.mygroceryshop.data.exception.NetworkErrorException
import retrofit2.Response

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {
    /**
     * handle result from network call
     * */
    @Throws(Exception::class)
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): T {
        try {

            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return body
            }
            throw NetworkErrorException(400, "unable to get data!")
        } catch (e: Exception) {
            throw e
        }
    }
}

