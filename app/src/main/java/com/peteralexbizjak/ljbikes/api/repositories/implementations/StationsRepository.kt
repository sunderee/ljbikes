package com.peteralexbizjak.ljbikes.api.repositories.implementations

import com.peteralexbizjak.ljbikes.BuildConfig
import com.peteralexbizjak.ljbikes.api.models.stations.StationModel
import com.peteralexbizjak.ljbikes.api.repositories.IStationsRepository
import com.peteralexbizjak.ljbikes.api.services.StationsService

internal class StationsRepository(
    private val stationsService: StationsService
) : IStationsRepository {
    override suspend fun getStationsInfo(): List<StationModel> {
        val developerApiKey = BuildConfig.API_KEY
        return stationsService.getStationsInfo(developerApiKey = developerApiKey)
    }
}