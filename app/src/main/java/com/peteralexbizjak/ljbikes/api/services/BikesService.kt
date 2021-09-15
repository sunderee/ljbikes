package com.peteralexbizjak.ljbikes.api.services

import com.peteralexbizjak.ljbikes.api.models.bikes.BikeModel
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

internal interface BikesService {
    @Headers(
        "host: api.cyclocity.fr",
        "accept: application/vnd.bikes.v3+json",
        "user-agent: okhttp/4.9.0 vls.android.ljubljana:PRD/1.19.2"
    )
    @GET("contracts/ljubljana/bikes")
    suspend fun requestBikesForStation(@Query("stationNumber") stationID: Int): List<BikeModel>
}