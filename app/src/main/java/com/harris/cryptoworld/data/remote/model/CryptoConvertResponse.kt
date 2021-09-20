package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName
import com.harris.cryptoworld.domain.model.CryptoConvert

class CryptoConvertResponse : ApiResponse() {

    @SerializedName("info")
    val info: CryptoConvertInfo? = null

    @SerializedName("result")
    val result: Double = 0.0

    fun toBean() = CryptoConvert(result, info?.rate ?: 0.0)
}

class CryptoConvertInfo {
    @SerializedName("rate")
    val rate: Double = 0.0

}