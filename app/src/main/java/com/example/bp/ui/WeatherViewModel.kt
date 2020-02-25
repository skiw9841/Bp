package com.example.bp.com.example.bp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.bp.com.example.bp.model.Location
import com.example.bp.com.example.bp.model.Weather
import com.example.bp.com.example.bp.repository.WeatherRepository

class WeatherViewModel(private val weatherRepository: WeatherRepository): ViewModel() {
    val requestLocationLiveData = MutableLiveData<String>() // query
    val requestWeatherLiveData = MutableLiveData<Long>() // woeid

    val responseLocationLiveData: LiveData<List<Location>> =
        Transformations.switchMap(requestLocationLiveData) { query: String ->
            weatherRepository.getLocationList(query)
        }


    val responseWeatherLiveData: LiveData<Weather> =
        Transformations.switchMap(requestWeatherLiveData) { woeid :Long ->
            weatherRepository.getWeather(woeid)
        }

}