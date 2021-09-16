package com.peteralexbizjak.ljbikes.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peteralexbizjak.ljbikes.api.models.ApiResponseModel
import com.peteralexbizjak.ljbikes.api.models.bikes.BikeModel
import com.peteralexbizjak.ljbikes.api.repositories.IBikesRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class StationFragmentViewModel(
    private val repository: IBikesRepository
) : ViewModel() {
    val bikesObserver by lazy { MutableLiveData<ApiResponseModel<List<BikeModel>>>() }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        bikesObserver.postValue(ApiResponseModel.Failure(throwable))
    }

    fun findBikesInformation(stationID: Int) {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            bikesObserver.postValue(ApiResponseModel.Loading)
            val bikesData = repository.requestBikesForStation(stationID)
            if (bikesData.isNotEmpty()) {
                bikesObserver.postValue(ApiResponseModel.Success(bikesData))
            }
        }
    }
}