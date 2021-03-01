package com.udacity.asteroidradar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json

@Entity(tableName = "picture_of_the_day")
data class PictureOfDay(
        @PrimaryKey

        @ColumnInfo(name = "media_type")
        @Json(name = "media_type")
        val mediaType: String,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "url")
        val url: String)
