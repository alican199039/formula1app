package com.aydemir.formula1app.model.remote

import com.aydemir.formula1app.model.data.DriverDetail
import com.aydemir.formula1app.model.data.Items
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("drivers")
    suspend fun getDrivers(): Response<Items>

    @GET("driverDetail/{id}")
    suspend fun getDriverDetail(@Path("id") id: Int): Response<DriverDetail>
}