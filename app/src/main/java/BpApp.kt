package com.example.bp

import android.app.Application
import com.example.bp.com.example.bp.di.apiModule
import com.example.bp.com.example.bp.di.weatherModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BpApp: Application(){
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@BpApp)
            // modules
            modules(listOf(apiModule, weatherModule))
        }

    }
}