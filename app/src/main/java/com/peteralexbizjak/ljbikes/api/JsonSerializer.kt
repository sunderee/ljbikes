package com.peteralexbizjak.ljbikes.api

import com.peteralexbizjak.ljbikes.utils.json
import kotlinx.serialization.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import java.lang.reflect.Type

@ExperimentalSerializationApi
internal class JsonSerializer {
    fun serializer(type: Type): KSerializer<Any> = json.serializersModule.serializer(type)

    fun <T> fromResponseBody(loader: DeserializationStrategy<T>, body: ResponseBody): T = json
        .decodeFromString(loader, body.byteStream().use { it.reader().readText() })

    fun <T> toRequestBody(saver: SerializationStrategy<T>, value: T): RequestBody = json
        .encodeToString(saver, value)
        .toRequestBody("application/json; charset=UTF-8".toMediaType())
}