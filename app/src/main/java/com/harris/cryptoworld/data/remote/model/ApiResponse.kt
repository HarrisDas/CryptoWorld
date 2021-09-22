package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName

open class ApiResponse {
    @SerializedName("success")
    var success: Boolean? = null

    @SerializedName("error")
    var error: ApiErrorResponse? = null
}