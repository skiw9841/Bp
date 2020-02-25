package com.example.bp.com.example.bp.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.bp.com.example.bp.di.BASE_URL
import com.google.gson.annotations.SerializedName
import java.io.Serializable
import kotlin.math.roundToLong

data class ConsolidatedWeather(
    @SerializedName("id")
    val id:Long,
    @SerializedName("weather_state_name")
    val weather_state_name:String,
    @SerializedName("weather_state_abbr")
    val weather_state_abbr:String,
    @SerializedName("applicable_date")
    val applicable_date:String,
    @SerializedName("the_temp")
    val the_temp:Float,
    @SerializedName("humidity")
    val humidity:Int

): Serializable {

    //	/static/img/weather/png/64/X.png
    fun getIconUrl(): String {
        return "$BASE_URL/static/img/weather/png/64/$weather_state_abbr.png"
    }

    fun formatTemp(): String {
        return "${the_temp.roundToLong()}ºC"
    }
    fun formatHumidity(): String {
        return "$humidity%"
    }

}

@BindingAdapter("bind_image" )
fun setImageUrl(view: ImageView, url: String?) {
    if(url!=null) {
        Glide.with(view.context)
            .load(url)
            .into(view)
    }
}
