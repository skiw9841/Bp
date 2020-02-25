package com.example.bp.com.example.bp.di

import com.example.bp.BuildConfig
import com.example.bp.com.example.bp.api.WeatherApi
import com.example.bp.com.example.bp.repository.WeatherRepository
import com.example.bp.com.example.bp.repository.WeatherRepositoryImpl
import com.example.bp.com.example.bp.ui.WeatherAdapter
import com.example.bp.com.example.bp.ui.WeatherViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://www.metaweather.com"

val apiModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(get())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }

    // OkHttpClient
    single {
        OkHttpClient.Builder().addInterceptor(get() as HttpLoggingInterceptor).build()
    }

    // HttpLoggingInterceptor
    single {
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE

            }
        }
    }

}


val weatherModule: Module = module {
    /* recyclerView adapter */
    single {
        WeatherAdapter()
    }

    /* repository */
    single {
        WeatherRepositoryImpl(get()) as WeatherRepository
    }

    /* viewModel */
    factory {
        WeatherViewModel(get())
    }

}