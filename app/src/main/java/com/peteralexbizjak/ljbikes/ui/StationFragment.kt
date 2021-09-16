package com.peteralexbizjak.ljbikes.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.peteralexbizjak.ljbikes.databinding.FragmentStationBinding
import com.peteralexbizjak.ljbikes.ui.viewmodels.StationFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

internal class StationFragment : Fragment() {
    private var bindingInstance: FragmentStationBinding? = null
    private val binding: FragmentStationBinding get() = bindingInstance!!

    private val navArguments by navArgs<StationFragmentArgs>()
    private val stationFragmentViewModel by viewModel<StationFragmentViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stationFragmentViewModel.findBikesInformation(navArguments.stationID)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingInstance = FragmentStationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.fragmentStationToolbar.setNavigationOnClickListener { findNavController().popBackStack() }
        binding.apply {
            stationInfoTitle = "${navArguments.stationName} - No. ${navArguments.stationID}"
            stationAddress = navArguments.stationAddress
            availableBikes = navArguments.availableBikes.toString()
            availableParking =
                (navArguments.totalBikeStands - navArguments.availableBikes).toString()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingInstance = null
    }
}