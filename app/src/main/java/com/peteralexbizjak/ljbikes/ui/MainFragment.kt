package com.peteralexbizjak.ljbikes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.peteralexbizjak.ljbikes.R
import com.peteralexbizjak.ljbikes.databinding.FragmentMainBinding
import com.peteralexbizjak.ljbikes.ui.maps.StationItem
import com.peteralexbizjak.ljbikes.ui.maps.StationRenderer

internal class MainFragment : Fragment() {
    private var bindingInstance: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = bindingInstance!!

    private val navigationArguments by navArgs<MainFragmentArgs>()

    private lateinit var mapFragment: SupportMapFragment

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingInstance = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapFragment =
            childFragmentManager.findFragmentById(R.id.fragmentMainMaps) as SupportMapFragment
        mapFragment.onCreate(savedInstanceState)
        mapFragment.getMapAsync {
            it.moveToCenterOfLjubljana()
            addStationMarkers(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInstance = null
    }

    override fun onDetach() {
        super.onDetach()
        requireActivity().window.clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
    }

    private fun GoogleMap.moveToCenterOfLjubljana() {
        this.animateCamera(
            CameraUpdateFactory.newCameraPosition(
                CameraPosition.fromLatLngZoom(
                    LatLng(46.0569, 14.5058),
                    7.5F
                )
            )
        )
    }

    private fun addStationMarkers(map: GoogleMap) {
        val manager = ClusterManager<StationItem>(context, map)
        manager.renderer = context?.let { StationRenderer(it, map, manager) }

        manager.addItems(navigationArguments.stations.map {
            StationItem(
                it.stationID,
                it.stationName,
                it.stationAddress,
                LatLng(it.geographicLocation.latitude, it.geographicLocation.longitude)
            )
        })
        manager.cluster()
        map.setOnCameraIdleListener { manager.onCameraIdle() }
    }
}