package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName

class CryptoListResponse : ApiResponse() {
    @SerializedName("crypto")
    val crypto: Map<String, RemoteCrypto>? = null
}