package com.peteralexbizjak.ljbikes.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.peteralexbizjak.ljbikes.BuildConfig
import com.peteralexbizjak.ljbikes.api.buildRetrofit
import kotlinx.serialization.ExperimentalSerializationApi

@ExperimentalSerializationApi
internal val retrofitInstanceFR = buildRetrofit(BuildConfig.BASE_URL_FR)

@ExperimentalSerializationApi
internal val retrofitInstance = buildRetrofit(BuildConfig.BASE_URL)

internal fun getSharedPreferences(androidApplication: Application): SharedPreferences =
    androidApplication.getSharedPreferences(
        "com.peteralexbizjak.ljbikes.preferences",
        Context.MODE_PRIVATE
    )