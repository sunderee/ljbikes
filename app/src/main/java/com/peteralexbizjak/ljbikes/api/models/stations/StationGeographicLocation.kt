package com.peteralexbizjak.ljbikes.api.models.stations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StationGeographicLocation(
    @SerialName("lat")
    val latitude: Double,

    @SerialName("lng")
    val longitude: Double
)