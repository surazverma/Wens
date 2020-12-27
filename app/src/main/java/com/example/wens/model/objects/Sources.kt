package com.example.wens.model.objects

import com.google.gson.annotations.SerializedName

data class Sources(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)