package com.example.webanttes.data.remote

import com.example.webanttes.BuildConfig
import com.example.webanttes.data.network.ApiResponse
import com.example.webanttes.data.remote.request.OrderBy
import com.example.webanttes.data.remote.response.ResponsePhoto
import com.example.webanttes.data.remote.response.ResponseSearchPhoto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface UnsplashApi {

    @GET("/photos")
    suspend fun getPhotos(
        @Query("client_id") token: String = BuildConfig.ACCESS_KEY,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: OrderBy?
    ): ApiResponse<List<ResponsePhoto>>

    @GET("/search/photos")
    suspend fun searchPhotos(
        @Query("client_id") token: String = BuildConfig.ACCESS_KEY,
        @Query("query") query: String?,
        @Query("page") page: Int?,
        @Query("per_page") perPage: Int?,
        @Query("order_by") orderBy: OrderBy?
    ): ApiResponse<ResponseSearchPhoto>
}