package com.example.bp.com.example.bp.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bp.R
import com.example.bp.com.example.bp.model.Weather
import com.example.bp.databinding.ItemWeatherBinding


class WeatherAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    private var weatherList = ArrayList<Weather>()
    private val _header = 0
    private val _items = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            _header -> HeaderViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather_header, parent, false))
            _items -> WeatherViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_weather, parent, false))
            else -> throw RuntimeException("Could not inflate layout")
        }
    }

    override fun getItemCount(): Int {
        return weatherList.size + 1
    }

    fun setRecyclerView(view: RecyclerView) {
        view.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                Log.d("adapter", "scrolled $dx, $dy" )
            }
        })
    }

    fun addWeather(weather: Weather) {
        Log.d("Adapter", "addWeather $weather")
        weatherList.add(weather)
    }

    fun notifyItemAll() {
        notifyItemRangeInserted(0, weatherList.size)
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == _header) _header else _items
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if( holder is WeatherViewHolder ) {
            holder.binding.weather = weatherList[position-1] // -1 header size
        }
    }

    inner class WeatherViewHolder(view: View): RecyclerView.ViewHolder(view) {
        var binding: ItemWeatherBinding = DataBindingUtil.bind(itemView)!!
    }

    inner class HeaderViewHolder(view: View): RecyclerView.ViewHolder(view)
}