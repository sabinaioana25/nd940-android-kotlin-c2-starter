package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface PictureOfDayDatabaseDao {

    @Query("SELECT * FROM databasepictureofday ")
    fun getPictures(): LiveData<List<DatabasePictureOfDay>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPictures(vararg pictures: DatabasePictureOfDay)
}

@Database(entities = [DatabasePictureOfDay:: class], version = 1)
abstract class PictureOfTheDayDatabase: RoomDatabase(){
    abstract val pictureOfTheDayDatabaseDao: PictureOfDayDatabaseDao
}

private lateinit var INSTANCE: PictureOfTheDayDatabase
fun getPicturesDatabase(context: Context): PictureOfTheDayDatabase {
    synchronized(PictureOfTheDayDatabase::class.java) {
        if (!:: INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
            PictureOfTheDayDatabase::class.java, "pictures of the day")
                    .build()
        }
    }
    return INSTANCE
}