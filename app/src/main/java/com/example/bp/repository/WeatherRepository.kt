package com.example.bp.com.example.bp.repository

import androidx.lifecycle.MutableLiveData
import com.example.bp.com.example.bp.model.Location
import com.example.bp.com.example.bp.model.Weather

interface WeatherRepository{
    fun getLocationList(query: String): MutableLiveData<List<Location>>
    fun getWeather(woeid: Long): MutableLiveData<Weather>
}

