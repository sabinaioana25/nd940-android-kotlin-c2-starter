package com.udacity.asteroidradar

object Constants {
    // asteroid list
    const val API_QUERY_DATE_FORMAT = "YYYY-MM-dd"
    const val DEFAULT_END_DATE_DAYS = 7
    const val BASE_URL = "https://api.nasa.gov/"
    const val MID_URL = "neo/rest/v1/feed?"
    const val PARAM_START_DATE = "start_date="
    const val START_DATE_VALUE = "2021-03-04"
    const val PARAM_END_DATE = "end_date="
    const val END_DATE_VALUE = "2021-03-11"
    const val PARAM_API_KEY = "api_key="
    const val API_KEY_VALUE = "P6E1j0fE8dd7sSipl1EEPkKf5L2qxy4Ct9FwxV5w"

    // astronomical picture of the day
    const val IMAGE_MID_URL = "planetary/apod?"
}