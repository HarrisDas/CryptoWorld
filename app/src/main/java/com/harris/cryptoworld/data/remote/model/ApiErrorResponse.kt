package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName

class ApiErrorResponse {
    @SerializedName("code")
    var code: Int? = null

    @SerializedName("type")
    var type: String? = null

    @SerializedName("info")
    var info: String? = null
}
