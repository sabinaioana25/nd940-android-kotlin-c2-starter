package com.udacity.asteroidradar.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.udacity.asteroidradar.PictureOfDay

@Entity
data class DatabasePictureOfDay constructor(
        @PrimaryKey
        @Json(name = "media_type")
        val mediaType: String,
        val title: String,
        val url: String)

fun List<DatabasePictureOfDay>.asDomainModel(): List<PictureOfDay> {
        return map {
                PictureOfDay(
                        mediaType = it.mediaType,
                        title = it.mediaType,
                        url = it.url)
        }
}