package com.example.listtest.service

import retrofit2.http.GET
import retrofit2.http.Query

interface ImageApiService {
    @GET("v2/list")
    suspend fun getImages(
        @Query("page") page: Int,
        @Query("limit") limit: Int = 5
    ): List<ImageItem>
}