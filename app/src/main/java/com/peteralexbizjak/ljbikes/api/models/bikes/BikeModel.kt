package com.peteralexbizjak.ljbikes.api.models.bikes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BikeModel(
    @SerialName("id")
    val bikeID: String,

    @SerialName("number")
    val bikeNumber: Int,

    @SerialName("standNumber")
    val standNumber: Int,

    @SerialName("rating")
    val bikeRating: BikeRatingModel,

    @SerialName("createdAt")
    val dateOfCreation: String,

    @SerialName("updatedAt")
    val lastUpdate: String
)