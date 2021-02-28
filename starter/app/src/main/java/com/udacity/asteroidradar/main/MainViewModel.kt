package com.udacity.asteroidradar.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.asteroidradar.api.AsteroidApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class MainViewModel : ViewModel() {

    // internal mutable LiveData String that stores the status of the most recent request
    private val _response = MutableLiveData<String>()

    // external immutable LiveData for the request status String
    val response: LiveData<String>
            get() = _response

    // call getAsteroidsFromRequest() on init so I can display status immediately

//    private val _responsePod = MutableLiveData<String>()
    private val _responseApod = MutableLiveData<String>()
    val responseApod: LiveData<String>
        get() = _responseApod

    init {
        getAsteroidsFromRequest()
        getAsteroidApodRequest()
    }

    private fun getAsteroidsFromRequest() {
        AsteroidApi.retrofitService.getAsteroidProperties().enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _response.value = response.body()
//                Timber.i(_response.value)
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _response.value = "Failure: " + t.message
            }
        })
//        _response.value = "Set Nasa APOD response here"
    }

    private fun getAsteroidApodRequest() {
        AsteroidApi.retrofitServicePod.getAsteroidApod().enqueue(object : Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                _responseApod.value = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                _responseApod.value = "Failure: " + t.message
            }
        })

        Timber.i(_responseApod.value)
    }
}