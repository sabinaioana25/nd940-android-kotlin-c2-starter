package com.udacity.asteroidradar.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.udacity.asteroidradar.PictureOfDay


@Database(entities = [PictureOfDay::class], version = 1, exportSchema = false)
abstract class ApodDatabase: RoomDatabase() {

    abstract val apodDatabaseDao: ApodDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: ApodDatabase? = null

        fun getInstance(context: Context): ApodDatabase {
            synchronized(this) {}
            var instance = INSTANCE
            if (instance == null) {
                instance = Room.databaseBuilder(
                        context.applicationContext,
                        ApodDatabase::class.java,
                        "picture of the day database"
                )
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return instance
        }
    }
}