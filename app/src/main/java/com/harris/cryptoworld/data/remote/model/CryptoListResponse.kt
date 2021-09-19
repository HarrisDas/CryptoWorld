package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName

class CryptoListResponse {
    @SerializedName("success")
    val success: Boolean? = null

    @SerializedName("body")
    var body: List<RemoteCrypto>? = null


}