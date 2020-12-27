package com.example.wens.model.responses

import com.google.gson.annotations.SerializedName

data class BaseListResponse<T>(
    @SerializedName("status") var status: String? = null,
    @SerializedName("totalResult") var totalResult: Int? = null,
    @SerializedName("articles") var data: List<T>? = null,
    @SerializedName("code") var code: String? = null,
    @SerializedName("message") var message: String? = null
)