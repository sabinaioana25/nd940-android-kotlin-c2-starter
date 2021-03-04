package com.udacity.asteroidradar

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "daily_asteroid_tracker_table")
data class Asteroid(
    @PrimaryKey(autoGenerate = true)
    @Json(name = "id")
                val id: Long,

    @ColumnInfo(name = "code_name")
    @Json(name = "name")
                val codename: String,

    @ColumnInfo(name = "close_approach_date")
                val closeApproachDate: String,

    @ColumnInfo(name = "absolute_magnitude")
                val absoluteMagnitude: Double,
    val estimatedDiameter: Double,

    @ColumnInfo(name = " relative_velocity")
                val relativeVelocity: Double,

    @ColumnInfo(name = "distance_from_earth")
                val distanceFromEarth: Double,

    @ColumnInfo(name = "is_potentially_hazardous")
                val isPotentiallyHazardous: Boolean)
    : Parcelable