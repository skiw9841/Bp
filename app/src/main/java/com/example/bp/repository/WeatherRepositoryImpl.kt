package com.example.bp.com.example.bp.repository

import androidx.lifecycle.MutableLiveData
import com.example.bp.com.example.bp.api.WeatherApi
import com.example.bp.com.example.bp.model.Location
import com.example.bp.com.example.bp.model.Weather
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherRepositoryImpl(private val api: WeatherApi) : WeatherRepository {

    override fun getLocationList(query: String): MutableLiveData<List<Location>> {
        val locationList = MutableLiveData<List<Location>>()

        api.getLocationList(query).enqueue(object :Callback<List<Location>>{
            override fun onFailure(call: Call<List<Location>>, t: Throwable) {
                locationList.value = null
            }

            override fun onResponse(call: Call<List<Location>>, response: Response<List<Location>>) {
                if (response.isSuccessful) {
                    if( response.body() != null ) {
                        locationList.value = response.body()!!
                    }
                }
            }
        })
        return locationList
    }

    override fun getWeather(woeid: Long): MutableLiveData<Weather> {
        val weather = MutableLiveData<Weather>()

        api.getWeather(woeid).enqueue(object :Callback<Weather?>{
            override fun onFailure(call: Call<Weather?>, t: Throwable) {
                weather.value = null
            }

            override fun onResponse(call: Call<Weather?>, response: Response<Weather?>) {
                if (response.isSuccessful) {
                    if( response.body() != null ) {
                        weather.value = response.body()!!
                    }
                }
            }
        })
        return weather
    }

}