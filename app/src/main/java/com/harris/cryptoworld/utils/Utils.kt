package com.harris.cryptoworld.utils

import com.harris.cryptoworld.presentation.UIState
import com.harris.cryptoworld.data.exception.AuthenticationException
import com.harris.cryptoworld.data.exception.NetworkErrorException
import retrofit2.HttpException
import timber.log.Timber
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

object Utils {
    fun resolveError(e: Exception): UIState.ErrorState {
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

        return UIState.ErrorState(error)
    }
}