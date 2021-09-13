package com.peteralexbizjak.ljbikes.api

import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private val loggingInterceptor = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BASIC)
        redactHeader("Authorization")
        redactHeader("Cookie")
    })
    .build()

@ExperimentalSerializationApi
internal fun buildRetrofit(baseURL: String): Retrofit = Retrofit.Builder()
    .baseUrl(baseURL)
    .client(loggingInterceptor)
    .addConverterFactory(JsonConverterFactory.create())
    .build()

internal fun <T> buildService(retrofit: Retrofit, service: Class<T>) = retrofit.create(service)