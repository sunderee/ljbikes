package com.peteralexbizjak.ljbikes.di

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.peteralexbizjak.ljbikes.BuildConfig
import com.peteralexbizjak.ljbikes.api.buildRetrofit
import com.peteralexbizjak.ljbikes.api.buildService
import com.peteralexbizjak.ljbikes.api.repositories.IStationsRepository
import com.peteralexbizjak.ljbikes.api.repositories.ITokenRepository
import com.peteralexbizjak.ljbikes.api.repositories.implementations.StationsRepository
import com.peteralexbizjak.ljbikes.api.repositories.implementations.TokenRepository
import com.peteralexbizjak.ljbikes.api.services.StationsService
import com.peteralexbizjak.ljbikes.api.services.TokenService
import com.peteralexbizjak.ljbikes.ui.viewmodels.LoadingFragmentViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalSerializationApi
private val retrofitInstanceFR = buildRetrofit(BuildConfig.BASE_URL_FR)

@ExperimentalSerializationApi
private val retrofitInstance = buildRetrofit(BuildConfig.BASE_URL)

fun getSharedPreferences(androidApplication: Application): SharedPreferences =
    androidApplication.getSharedPreferences(
        "com.peteralexbizjak.ljbikes.preferences",
        Context.MODE_PRIVATE
    )

@ExperimentalSerializationApi
internal val loadingFragmentModule = module {
    single { buildService(retrofitInstanceFR, TokenService::class.java) }
    single { getSharedPreferences(androidApplication = androidApplication()) }
    single<ITokenRepository> { TokenRepository(tokenService = get(), sharedPreferences = get()) }

    single { buildService(retrofitInstance, StationsService::class.java) }
    single<IStationsRepository> { StationsRepository(stationsService = get()) }

    viewModel { LoadingFragmentViewModel(tokenRepository = get(), stationsRepository = get()) }
}