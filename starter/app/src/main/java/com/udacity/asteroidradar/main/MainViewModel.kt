package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.PictureOfDay
import com.udacity.asteroidradar.api.AsteroidApi
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception

class MainViewModel : ViewModel() {

    // internal mutable LiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // external immutable LiveData for the request status String
    val response: LiveData<String>
            get() = _response

    // call getAsteroidsFromRequest() on init so I can display status immediately

//    private val _responsePod = MutableLiveData<String>()
    private val _responseApod = MutableLiveData<PictureOfDay>()
    val responseApod: LiveData<PictureOfDay>
        get() = _responseApod

    init {
        getAsteroidsFromRequest()
        getAsteroidApodRequest()
    }

    private fun getAsteroidsFromRequest() {
        AsteroidApi.retrofitService.getAsteroidProperties().enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }
        })
    }

    private fun getAsteroidApodRequest() {
        viewModelScope.launch {
            var getApod = AsteroidApi.retrofitServicePod.getAsteroidApod()
            try {
                var imgResult = getApod
                if (imgResult != null) {
                    _responseApod.value =  imgResult
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}