package com.example.homework27.data.source.remote.service

import com.example.homework27.data.source.remote.model.VehicleDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface VehiclesApiService {
    @GET("6f722f19-3297-4edd-a249-fe765e8104db")
    suspend fun searchByTitle(@Query("search") title: String): Response<List<VehicleDto>>
}