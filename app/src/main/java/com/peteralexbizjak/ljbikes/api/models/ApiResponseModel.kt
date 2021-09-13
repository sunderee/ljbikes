package com.peteralexbizjak.ljbikes.api.models

sealed class ApiResponseModel<out T> {
    data class Success<out T>(val value: T) : ApiResponseModel<T>()
    data class Failure(val throwable: Throwable?) : ApiResponseModel<Nothing>()
    object Loading : ApiResponseModel<Nothing>()
}