package com.peteralexbizjak.ljbikes.api.services

import com.peteralexbizjak.ljbikes.api.models.stations.StationModel
import retrofit2.http.GET
import retrofit2.http.Query

internal interface StationsService {
    @GET("/vls/v1/stations")
    suspend fun getStationsInfo(
        @Query("contract") cityName: String = "ljubljana",
        @Query("apiKey") developerApiKey: String
    ): List<StationModel>
}