package com.peteralexbizjak.ljbikes.api.repositories

import com.peteralexbizjak.ljbikes.api.models.bikes.BikeModel

internal interface IBikesRepository {
    suspend fun requestBikesForStation(stationID: Int): List<BikeModel>
}