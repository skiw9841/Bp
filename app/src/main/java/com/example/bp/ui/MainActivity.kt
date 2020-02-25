package com.example.bp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.bp.R
import com.example.bp.com.example.bp.model.Location
import com.example.bp.com.example.bp.model.Weather
import com.example.bp.com.example.bp.ui.WeatherAdapter
import com.example.bp.com.example.bp.ui.WeatherViewModel
import com.example.bp.databinding.ActivityMainBinding
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: WeatherViewModel by inject()
    private val adapter: WeatherAdapter by inject()

    /* request queue */
    private lateinit var woeidQueue: Queue<Long>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        /* adapter */
        adapter.setRecyclerView(binding.rvWeather)
        binding.rvWeather.adapter = adapter

        /* observe */
        subscribeData()

        if(savedInstanceState == null) { // for only first
            /* request query */
            viewModel.requestLocationLiveData.postValue("se")
            binding.progressBar.visibility = View.VISIBLE
        }
    }

    private fun subscribeData() {
        /* oberve Location list */
        viewModel.responseLocationLiveData.observe(this, Observer { list: List<Location> ->
            woeidQueue = LinkedList()
            for(location in list) {
                woeidQueue.add(location.woeid)
            }
            // request weather
            viewModel.requestWeatherLiveData.postValue(woeidQueue.poll())
        })

        viewModel.responseWeatherLiveData.observe(this, Observer {weather: Weather ->
            adapter.addWeather(weather)

            if( woeidQueue.peek()!=null ) { // queue is not empty
                viewModel.requestWeatherLiveData.postValue(woeidQueue.poll())
            } else {
                adapter.notifyItemAll() // draw list
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("query", "se")
    }

}
