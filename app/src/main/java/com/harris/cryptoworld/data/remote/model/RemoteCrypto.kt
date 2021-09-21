package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName
import com.harris.cryptoworld.domain.model.Crypto

class RemoteCrypto() {
    @SerializedName("name")
    val name: String? = null

    @SerializedName("symbol")
    val symbol: String? = null

    @SerializedName("name_full")
    val name_full: String? = null

    @SerializedName("icon_url")
    val iconUrl: String? = null

    fun toBean() = Crypto(name ?: "", symbol ?: "",iconUrl?:"")

}