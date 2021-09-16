package com.peteralexbizjak.ljbikes.api.repositories.implementations

import com.peteralexbizjak.ljbikes.api.models.bikes.BikeModel
import com.peteralexbizjak.ljbikes.api.repositories.IBikesRepository
import com.peteralexbizjak.ljbikes.api.services.BikesService

internal class BikesRepository(
    private val bikesService: BikesService
) : IBikesRepository {
    override suspend fun requestBikesForStation(stationID: Int): List<BikeModel> {
        return bikesService.requestBikesForStation(stationID)
    }
}