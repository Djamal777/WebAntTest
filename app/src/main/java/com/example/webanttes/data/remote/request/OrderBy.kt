package com.example.webanttes.data.remote.request

import com.google.gson.annotations.SerializedName

enum class OrderBy {
    @SerializedName("latest")
    latest,
    @SerializedName("oldest")
    oldest,
    @SerializedName("popular")
    popular
}