package com.harris.mygroceryshop.domain.datasource

import com.harris.mygroceryshop.domain.State
import com.harris.mygroceryshop.domain.exception.NetworkErrorException
import retrofit2.Response

/**
 * Abstract Base Data source class with error handling
 */
abstract class BaseDataSource {
    /**
     * handle result from network call
     * */
    protected suspend fun <T> getResult(call: suspend () -> Response<T>): State<T> {
        try {

            val response = call()
            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) return State.DataState(body)
            }
            throw NetworkErrorException(400, "unable to get data!")
        } catch (e: Exception) {
            throw e
        }
    }
}

