package com.peteralexbizjak.ljbikes.api.models.stations

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StationModel(
    @SerialName("number")
    val stationID: Int,

    @SerialName("name")
    val stationName: String,

    @SerialName("address")
    val stationAddress: String,

    @SerialName("position")
    val geographicLocation: StationGeographicLocation,

    @SerialName("bike_stands")
    val totalStands: Int,

    @SerialName("available_bike_stands")
    val availableBikeStands: Int,

    @SerialName("available_bikes")
    val availableBikes: Int,

    @SerialName("status")
    val stationStatus: String,

    @SerialName("last_update")
    val stationDataLastUpdateUnix: Int
)
