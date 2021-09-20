package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName

class ApiErrorResponse {
    @SerializedName("code")
    val code: Int? = null

    @SerializedName("type")
    val type: String? = null

    @SerializedName("info")
    val info: String? = null
}
