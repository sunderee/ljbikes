package com.peteralexbizjak.ljbikes.api

import com.peteralexbizjak.ljbikes.BuildConfig
import kotlinx.serialization.ExperimentalSerializationApi
import retrofit2.Retrofit

@ExperimentalSerializationApi
internal fun buildRetrofit(): Retrofit = Retrofit.Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .addConverterFactory(JsonConverterFactory.create())
    .build()

internal fun <T> buildService(retrofit: Retrofit, service: Class<T>) = retrofit.create(service)