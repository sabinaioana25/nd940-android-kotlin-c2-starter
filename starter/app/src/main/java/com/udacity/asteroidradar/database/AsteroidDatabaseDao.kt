package com.udacity.asteroidradar.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.udacity.asteroidradar.Asteroid

@Dao
interface AsteroidDatabaseDao {

    @Insert
    fun insert(asteroid: Asteroid)

    @Update
    fun update(asteroid: Asteroid)

    @Query("SELECT * FROM daily_asteroid_tracker_table WHERE id = :key")
    fun get(key: Long): Asteroid

    @Query("DELETE FROM daily_asteroid_tracker_table")
    fun clear()

    @Query("SELECT * FROM daily_asteroid_tracker_table ORDER BY id DESC ")
    fun getAllAsteroids(): LiveData<List<Asteroid>>
}