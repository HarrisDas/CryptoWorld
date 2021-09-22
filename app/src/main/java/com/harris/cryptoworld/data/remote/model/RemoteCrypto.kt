package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName
import com.harris.cryptoworld.domain.model.Crypto

class RemoteCrypto() {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("symbol")
    var symbol: String? = null

    @SerializedName("name_full")
    var name_full: String? = null

    @SerializedName("icon_url")
    var iconUrl: String? = null

    fun toBean() = Crypto(name ?: "", symbol ?: "",iconUrl?:"")

}