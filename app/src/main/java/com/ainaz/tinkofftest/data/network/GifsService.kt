package com.ainaz.tinkofftest.data.network

import com.ainaz.tinkofftest.data.network.model.GifResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GifsService {
    @GET("/latest/{page}")
    suspend fun getLatestGifs(
        @Path("page") page: Int,
        @Query("json") json: Boolean = true
    ): GifResponseDto


    @GET("/hot/{page}")
    suspend fun getHotGifs(
        @Path("page") page: Int,
        @Query("json") json: Boolean = true
    ): GifResponseDto


    @GET("/top/{page}")
    suspend fun getTopGifs(
        @Path("page") page: Int,
        @Query("json") json: Boolean = true
    ): GifResponseDto
}