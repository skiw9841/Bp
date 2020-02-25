package com.example.bp.com.example.bp.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Weather(
    @SerializedName("consolidated_weather")
    val consolidated_weather: List<ConsolidatedWeather>,
    @SerializedName("title")
    val title: String
): Serializable