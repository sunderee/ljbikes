package com.peteralexbizjak.ljbikes.utils

import kotlinx.serialization.json.Json

internal val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}
