package com.peteralexbizjak.ljbikes.api

import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

@ExperimentalSerializationApi
internal class JsonConverterFactory private constructor(
    private val serializer: JsonSerializer
) : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> = Converter<ResponseBody, Any> { value ->
        serializer.fromResponseBody(serializer.serializer(type), value)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<out Annotation>,
        methodAnnotations: Array<out Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody> = Converter<Any, RequestBody> { value ->
        serializer.toRequestBody(serializer.serializer(type), value)
    }

    companion object {
        @JvmStatic
        fun create(): JsonConverterFactory = JsonConverterFactory(serializer = JsonSerializer())
    }
}