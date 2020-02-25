package com.example.bp.com.example.bp.api

import com.example.bp.com.example.bp.model.Location
import com.example.bp.com.example.bp.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {

    // https://www.metaweather.com/api/location/search/?query=se
    @GET("/api/location/search")
    fun getLocationList(@Query("query") query:String): Call<List<Location>>

    @GET("/api/location/{woeid}")
    fun getWeather(@Path("woeid")woeid: Long): Call<Weather>
}