package com.peteralexbizjak.ljbikes.ui.maps

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.Cluster
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.view.DefaultClusterRenderer
import com.peteralexbizjak.ljbikes.R
import com.peteralexbizjak.ljbikes.api.models.stations.StationModel
import com.peteralexbizjak.ljbikes.utils.bitmapDescriptorFromVector

internal class StationRenderer(
    private val context: Context,
    lifecycleOwner: LifecycleOwner,
    stationRendererViewModel: StationRendererViewModel,
    map: GoogleMap,
    clusterManager: ClusterManager<StationModel>,
) : DefaultClusterRenderer<StationModel>(context, map, clusterManager) {
    private var currentZoom: Float
    private val stationMarker by lazy { context.bitmapDescriptorFromVector(R.drawable.ic_pin) }

    init {
        currentZoom = 0.0F
        stationRendererViewModel.getZoomLevel().observe(lifecycleOwner) {
            currentZoom = it
        }
    }

    override fun onBeforeClusterItemRendered(item: StationModel, markerOptions: MarkerOptions) {
        markerOptions.icon(stationMarker).position(item.position)
    }

    override fun onClusterItemRendered(clusterItem: StationModel, marker: Marker) {
        marker.tag = clusterItem
    }

    override fun shouldRenderAsCluster(cluster: Cluster<StationModel>): Boolean {
        return cluster.size >= 1 && currentZoom < 13.5
    }
}