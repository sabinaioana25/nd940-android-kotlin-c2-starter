package com.udacity.asteroidradar.database

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.udacity.asteroidradar.Asteroid
import com.udacity.asteroidradar.PictureOfDay

@JsonClass(generateAdapter = true)
data class NetworkAsteroidContainer(val asteroids: List<NetworkAsteroid>)

@JsonClass(generateAdapter = true)
data class NetworkAsteroid (
        val id: Long,
        val codename: String,
        val closeApproachDate: String,
        val absoluteMagnitude: Double,
        val estimatedDiameter: Double,
        val relativeVelocity: Double,
        val distanceFromEarth: Double,
        val isPotentiallyHazardous: Boolean)


fun NetworkAsteroidContainer.asDomainModel(): List<Asteroid> {
    return asteroids.map {
        Asteroid (
                id = it.id,
                codename = it.codename,
                closeApproachDate = it.closeApproachDate,
                absoluteMagnitude = it.absoluteMagnitude,
                estimatedDiameter = it.estimatedDiameter,
                relativeVelocity = it.relativeVelocity,
                distanceFromEarth = it.distanceFromEarth,
                isPotentiallyHazardous = it.isPotentiallyHazardous)
            }
        }

fun NetworkAsteroidContainer.asDatabaseModel(): Array<DatabaseAsteroid> {
    return asteroids.map {
        DatabaseAsteroid (
                id = it.id,
                codename = it.codename,
                closeApproachDate = it.closeApproachDate,
                absoluteMagnitude = it.absoluteMagnitude,
                estimatedDiameter = it.estimatedDiameter,
                relativeVelocity = it.relativeVelocity,
                distanceFromEarth = it.distanceFromEarth,
                isPotentiallyHazardous = it.isPotentiallyHazardous
                )
    }.toTypedArray()
}

@JsonClass(generateAdapter = true)
data class NetworkPictureOfDayContainer(val pictures: List<NetworkPicture>)

@JsonClass(generateAdapter = true)
data class NetworkPicture (
        val mediaType: String,
        val title: String,
        val url: String)

fun NetworkPictureOfDayContainer.asDomainModel(): List<PictureOfDay> {
    return pictures.map {
        PictureOfDay(
                mediaType = it.mediaType,
                title = it.title,
                url = it.url)
    }
}

fun NetworkPictureOfDayContainer.asDatabaseModel(): Array<DatabasePictureOfDay> {
    return pictures.map {
        DatabasePictureOfDay(
                mediaType = it.mediaType,
                title = it.title,
                url = it.url)
    }.toTypedArray()
}

