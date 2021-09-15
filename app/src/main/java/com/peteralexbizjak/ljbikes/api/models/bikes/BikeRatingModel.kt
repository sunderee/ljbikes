package com.peteralexbizjak.ljbikes.api.models.bikes

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class BikeRatingModel(
    @SerialName("value")
    val score: Double,

    @SerialName("count")
    val numberOfRatings: Int,

    @SerialName("lastRatingDateTime")
    val lastRating: String
)