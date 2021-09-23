package com.harris.cryptoworld.utils

import android.content.Context
import android.net.ConnectivityManager

class NetworkUtils(
    val context:
    Context
) {

    fun isInternetConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}