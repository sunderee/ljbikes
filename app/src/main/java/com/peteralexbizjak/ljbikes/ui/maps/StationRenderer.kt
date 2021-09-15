package com.peteralexbizjak.ljbikes.ui.maps

import android.content.Context
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.peteralexbizjak.ljbikes.R
import com.peteralexbizjak.ljbikes.utils.bitmapDescriptorFromVector

internal class StationRenderer(
    private val context: Context,
    map: GoogleMap,
    clusterManager: ClusterManager<StationItem>
) : DefaultClusterRenderer<StationItem>(context, map, clusterManager) {
    private val stationMarker by lazy { context.bitmapDescriptorFromVector(R.drawable.ic_pin) }

    override fun onBeforeClusterItemRendered(item: StationItem, markerOptions: MarkerOptions) {
        markerOptions.icon(stationMarker).position(item.position)
    }

    override fun onClusterItemRendered(clusterItem: StationItem, marker: Marker) {
        marker.tag = clusterItem
    }

    override fun shouldRenderAsCluster(cluster: Cluster<StationItem>): Boolean {
        return cluster.size > 1;
    }
}