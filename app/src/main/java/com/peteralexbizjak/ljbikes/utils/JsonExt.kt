package com.peteralexbizjak.ljbikes.utils

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

internal val json = Json {
    ignoreUnknownKeys = true
    isLenient = true
}

@ExperimentalSerializationApi
internal inline fun <reified T> T.serialize(): String = json.encodeToString(this)

@ExperimentalSerializationApi
internal inline fun <reified T> String.deserialize(): T = json.decodeFromString(this)