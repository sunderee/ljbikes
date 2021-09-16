package com.peteralexbizjak.ljbikes.api.models.stations

import android.os.Parcelable
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class StationModel(
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

    @SerialName("available_bikes")
    val availableBikes: Int,

    @SerialName("status")
    val stationStatus: String,

    @SerialName("last_update")
    val stationDataLastUpdateUnix: Long
) : Parcelable, ClusterItem {
    override fun getPosition(): LatLng = LatLng(
        geographicLocation.latitude,
        geographicLocation.longitude
    )

    override fun getTitle(): String = stationName

    override fun getSnippet(): String = stationAddress
}
