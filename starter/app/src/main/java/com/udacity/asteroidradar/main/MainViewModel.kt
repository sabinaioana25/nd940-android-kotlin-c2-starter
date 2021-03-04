package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import com.udacity.asteroidradar.api.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.api.parseAsteroidsJsonResult
import kotlinx.coroutines.launch
import org.json.JSONObject
import timber.log.Timber
import java.lang.Exception
import java.sql.Time

class MainViewModel : ViewModel() {

    // internal mutable LiveData String that stores the status of the most recent request
    private val _status = MutableLiveData<String>()
    val status: LiveData<String>
            get() = _status

    // private val _responsePod = MutableLiveData<String>()
    private val _responseApod = MutableLiveData<PictureOfDay>()
    val responseApod: LiveData<PictureOfDay>
        get() = _responseApod

    init {
        getAsteroidsFromRequest()
        getAsteroidApodRequest()
    }

    private fun getAsteroidsFromRequest() {
//        AsteroidApi.retrofitService.getAsteroidProperties().enqueue(object: Callback<List<Asteroid>> {
//            override fun onResponse(call: Call<List<Asteroid>>, response: Response<List<Asteroid>>) {
//                _response.value = response?.body().toString()
//                Timber.i("what is it??")
//            }
//
//            override fun onFailure(call: Call<List<Asteroid>>, t: Throwable) {
//                _response.value = "Failure: " + t.message
//            }
//        })
        viewModelScope.launch {
            val asteroidResult = AsteroidApi.retrofitService.getAsteroidProperties(Constants.API_KEY_VALUE,
                    getNextSevenDaysFormattedDates()[0],
                    getNextSevenDaysFormattedDates()[7])
            val asteroids = parseAsteroidsJsonResult(JSONObject(asteroidResult))
            Timber.e("after method: $asteroids")

            try {
                _status.value = asteroids.toString()

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun getAsteroidApodRequest() {
        viewModelScope.launch {
            var getApod = AsteroidApi.retrofitServicePod.getAsteroidApod()
            try {
                var imgResult = getApod
                _responseApod.value =  imgResult
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}