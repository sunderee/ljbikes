package com.peteralexbizjak.ljbikes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.peteralexbizjak.ljbikes.di.loadingFragmentModule
import kotlinx.serialization.ExperimentalSerializationApi
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

@ExperimentalSerializationApi
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainActivity.application)
            modules(loadingFragmentModule)
        }
        setContentView(R.layout.activity_main)
    }
}