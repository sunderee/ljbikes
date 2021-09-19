package com.peteralexbizjak.ljbikes.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.peteralexbizjak.ljbikes.api.models.stations.StationModel

internal class SearchFragmentViewModel : ViewModel() {
    private val stationsListInstance by lazy { mutableListOf<StationModel>() }
    val stationsObservable by lazy { MutableLiveData<List<StationModel>>() }

    fun setInitialList(stationsList: List<StationModel>) {
        stationsListInstance.addAll(stationsList)
    }

    fun applyFilter(filter: String) {
        stationsObservable.value = stationsListInstance.filter {
            it.stationName.lowercase().contains(filter.lowercase()) ||
                    it.stationAddress.lowercase().contains(filter.lowercase())
        }
    }
}