package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName

class CryptoConvertResponse : ApiResponse() {
    @SerializedName("info")
    val info: CryptoConvertInfo? = null

    @SerializedName("result")
    val result: Double? = null
}

class CryptoConvertInfo {
    @SerializedName("rate")
    val rate: Double = 0.0

}