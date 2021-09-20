package com.harris.cryptoworld.data.remote.model

import com.google.gson.annotations.SerializedName

open class ApiResponse {
    @SerializedName("success")
    val success: Boolean? = null

    @SerializedName("error")
    val error: ApiErrorResponse? = null
}