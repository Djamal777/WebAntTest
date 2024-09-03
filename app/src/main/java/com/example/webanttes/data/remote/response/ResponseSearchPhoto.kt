package com.example.webanttes.data.remote.response

import com.google.gson.annotations.SerializedName

data class ResponseSearchPhoto(
    @SerializedName("total")
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("results")
    val results: List<ResponsePhoto>
)