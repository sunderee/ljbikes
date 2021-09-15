package com.peteralexbizjak.ljbikes.api

import android.content.SharedPreferences
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private fun tokenAuthenticationClient(preferences: SharedPreferences) = OkHttpClient.Builder()
    .addInterceptor(Interceptor { chain ->
        val builder = chain.request().newBuilder()
        builder.addHeader("Authorization", "Taknv1 ${preferences.getString("access_token", null)}")
        return@Interceptor chain.proceed(builder.build())
    })
    .build()

@ExperimentalSerializationApi
internal fun buildRetrofit(
    baseURL: String,
    withTokenAuthClient: Boolean = false,
    sharedPreferences: SharedPreferences? = null
): Retrofit {
    val retrofitInstance = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(JsonConverterFactory.create())
    if (withTokenAuthClient && sharedPreferences != null) {
        retrofitInstance.client(tokenAuthenticationClient(sharedPreferences))
    }
    return retrofitInstance.build()
}

internal fun <T> buildService(retrofit: Retrofit, service: Class<T>) = retrofit.create(service)