package com.udacity.asteroidradar.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.udacity.asteroidradar.PictureOfDay as PictureOfDay1

@Dao
interface ApodDatabaseDao {

    @Insert
    fun insert(pictureOfDay: PictureOfDay1)

    @Update
    fun update(pictureOfDay: PictureOfDay1)


}