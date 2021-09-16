package com.peteralexbizjak.ljbikes.ui.maps

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

internal class StationRendererViewModel : ViewModel() {
    private val currentZoomLevel by lazy { MutableLiveData<Float>() }

    fun setNewZoomLevel(newZoomLevel: Float) {
        currentZoomLevel.value = newZoomLevel
    }

    fun getZoomLevel(): LiveData<Float> = currentZoomLevel
}