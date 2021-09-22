package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName
import com.harris.cryptoworld.domain.model.CryptoConvert

class CryptoConvertResponse : ApiResponse() {

    @SerializedName("info")
    var info: CryptoConvertInfo? = null

    @SerializedName("result")
    var result: Double = 0.0

    fun toBean() = CryptoConvert(result, info?.rate ?: 0.0)
}

class CryptoConvertInfo {
    @SerializedName("rate")
    var rate: Double = 0.0

}