package com.peteralexbizjak.ljbikes.ui.maps

import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem

internal data class StationItem(
    val stationID: Int,
    val name: String,
    val address: String,
    val location: LatLng
) : ClusterItem {
    override fun getPosition(): LatLng = location
    override fun getTitle(): String = name
    override fun getSnippet(): String = address
}