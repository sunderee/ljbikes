package com.peteralexbizjak.ljbikes.api.models.tokens

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class TokenRequestModel(
    @SerialName("code")
    val tokenRequestCode: String,

    @SerialName("key")
    val tokenRequestKey: String
)