package com.udacity.asteroidradar.api

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.net.URLEncoder

private const val BASE_URL = "https://api.nasa.gov/"
private const val MID_URL = "neo/rest/v1/feed?"
private const val PARAM_START_DATE = "start_date="
private const val START_DATE_VALUE = "2021-02-24"
private const val PARAM_END_DATE = "end_date="
private const val END_DATE_VALUE = "2021-02-26"
private const val PARAM_API_KEY = "api_key="
private const val API_KEY_VALUE = "P6E1j0fE8dd7sSipl1EEPkKf5L2qxy4Ct9FwxV5w"

private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
//        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

// implement the AsteroidApiService with @GET returning String
interface AsteroidApiService {
    @GET(MID_URL + PARAM_START_DATE + START_DATE_VALUE + "&" +
    PARAM_END_DATE + END_DATE_VALUE + "&" +
    PARAM_API_KEY + API_KEY_VALUE)

//    @GET("neo/rest/v1/feed?start_date=2021-02-24&end_date=2021-02-26&api_key=P6E1j0fE8dd7sSipl1EEPkKf5L2qxy4Ct9FwxV5w")
    fun getAsteroidProperties():
            Call<String>
}

// create the AsteroidApi object using retrofit to implement the AsteroidApiService
object AsteroidApi {
    val retrofitService: AsteroidApiService by lazy {
        retrofit.create(AsteroidApiService::class.java)
    }
}
