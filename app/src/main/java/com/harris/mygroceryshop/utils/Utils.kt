package com.harris.mygroceryshop.utils

import com.harris.mygroceryshop.domain.State
import com.harris.mygroceryshop.domain.exception.AuthenticationException
import com.harris.mygroceryshop.domain.exception.NetworkErrorException
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Utils {
    fun resolveError(e: Exception): State.ErrorState {
        Timber.e("request failed due to $e")
        var error = e

        when (e) {
            is SocketTimeoutException -> {
                error = NetworkErrorException(errorMessage = "connection error!")
            }
            is ConnectException -> {
                error = NetworkErrorException(errorMessage = "no internet access!")
            }
            is UnknownHostException -> {
                error = NetworkErrorException(errorMessage = "no internet access!")
            }
        }

        if (e is HttpException) {
            when (e.code()) {
                502 -> {
                    error = NetworkErrorException(e.code(), "internal error!")
                }
                401 -> {
                    throw AuthenticationException("authentication error!")
                }
                400 -> {
                    error = NetworkErrorException.parseException(e)
                }
            }
        }

        return State.ErrorState(error)
    }
}