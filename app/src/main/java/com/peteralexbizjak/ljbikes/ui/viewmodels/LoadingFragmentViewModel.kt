package com.peteralexbizjak.ljbikes.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.peteralexbizjak.ljbikes.api.models.ApiResponseModel
import com.peteralexbizjak.ljbikes.api.models.stations.StationModel
import com.peteralexbizjak.ljbikes.api.repositories.IStationsRepository
import com.peteralexbizjak.ljbikes.api.repositories.ITokenRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

internal class LoadingFragmentViewModel(
    private val tokenRepository: ITokenRepository,
    private val stationsRepository: IStationsRepository
) : ViewModel() {
    val stationsObservable by lazy { MutableLiveData<ApiResponseModel<List<StationModel>>>() }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
        stationsObservable.postValue(ApiResponseModel.Failure(throwable))
    }

    fun launchInitialConfiguration() {
        viewModelScope.launch(Dispatchers.IO + coroutineExceptionHandler) {
            stationsObservable.postValue(ApiResponseModel.Loading)
            tokenRepository.persistClientTokens()

            val stationsData = stationsRepository.getStationsInfo()
            if (stationsData.isNotEmpty()) {
                stationsObservable.postValue(ApiResponseModel.Success(stationsData))
            }
        }
    }
}