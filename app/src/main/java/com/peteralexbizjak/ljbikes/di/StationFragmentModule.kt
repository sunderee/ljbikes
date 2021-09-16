package com.peteralexbizjak.ljbikes.di

import com.peteralexbizjak.ljbikes.BuildConfig
import com.peteralexbizjak.ljbikes.api.buildRetrofit
import com.peteralexbizjak.ljbikes.api.buildService
import com.peteralexbizjak.ljbikes.api.repositories.IBikesRepository
import com.peteralexbizjak.ljbikes.api.repositories.implementations.BikesRepository
import com.peteralexbizjak.ljbikes.api.services.BikesService
import com.peteralexbizjak.ljbikes.ui.viewmodels.StationFragmentViewModel
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

@ExperimentalSerializationApi
internal val stationFragmentModule = module {
    single { getSharedPreferences(androidApplication = androidApplication()) }
    single {
        buildService(
            buildRetrofit(
                BuildConfig.BASE_URL_FR,
                withTokenAuthClient = true,
                sharedPreferences = get()
            ), BikesService::class.java
        )
    }
    single<IBikesRepository> { BikesRepository(bikesService = get()) }

    viewModel { StationFragmentViewModel(repository = get()) }
}