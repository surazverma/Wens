package com.example.wens.model.objects

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Sources(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
) :Parcelable