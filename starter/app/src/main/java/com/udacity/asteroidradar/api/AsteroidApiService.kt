package com.udacity.asteroidradar.api

import android.util.Log
import com.github.ajalt.timberkt.Timber
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.Constants
import com.udacity.asteroidradar.Constants.API_KEY_VALUE
import com.udacity.asteroidradar.Constants.BASE_URL
import com.udacity.asteroidradar.Constants.END_DATE_VALUE
import com.udacity.asteroidradar.Constants.IMAGE_MID_URL
import com.udacity.asteroidradar.Constants.MID_URL
import com.udacity.asteroidradar.Constants.PARAM_API_KEY
import com.udacity.asteroidradar.Constants.PARAM_END_DATE
import com.udacity.asteroidradar.Constants.PARAM_START_DATE
import com.udacity.asteroidradar.Constants.START_DATE_VALUE
import com.udacity.asteroidradar.PictureOfDay
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.net.URLEncoder

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

private val retrofitPod = Retrofit.Builder()
//        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

val timeywimey = getNextSevenDaysFormattedDates()[0]
// implement the AsteroidApiService with @GET returning String
interface AsteroidApiService {
//    @GET(MID_URL + PARAM_START_DATE + timeywimey + "&" +
//    PARAM_END_DATE + END_DATE_VALUE + "&" +
//    PARAM_API_KEY + API_KEY_VALUE)
    @GET("neo/rest/v1/feed")
    suspend fun getAsteroidProperties(
        @Query("api_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String):
//            Call<List<Asteroid>>
            String

    @GET(IMAGE_MID_URL + PARAM_API_KEY + API_KEY_VALUE)
    suspend fun getAsteroidApod():
           PictureOfDay
}

// create the AsteroidApi object using retrofit to implement the AsteroidApiService
object AsteroidApi {
    val retrofitService: AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }

    val retrofitServicePod: AsteroidApiService by lazy {
        retrofitPod.create(AsteroidApiService::class.java)
    }
}


