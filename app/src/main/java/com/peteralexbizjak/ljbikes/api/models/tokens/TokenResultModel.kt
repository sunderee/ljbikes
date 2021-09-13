package com.peteralexbizjak.ljbikes.api.models.tokens

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TokenResultModel(
    @SerialName("refreshToken")
    val refreshToken: String,

    @SerialName("accessToken")
    val token: String
)