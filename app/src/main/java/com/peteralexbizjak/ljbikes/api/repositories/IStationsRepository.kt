package com.peteralexbizjak.ljbikes.api.repositories

import com.peteralexbizjak.ljbikes.api.models.stations.StationModel

internal interface IStationsRepository {
    suspend fun getStationsInfo(): List<StationModel>
}