package com.shorbgy.paging.network

import com.shorbgy.paging.pojo.Cat
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {
    @GET("v1/images/search")
    suspend fun getCatImages(
        @Query("limit") size: Int,
        @Query("order") order: String = "Asc",
        @Query("page") page: Int
    ): List<Cat>
}