package com.peteralexbizjak.ljbikes.api.models.stations

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class StationGeographicLocation(
    @SerialName("lat")
    val latitude: Double,

    @SerialName("lng")
    val longitude: Double
) : Parcelable