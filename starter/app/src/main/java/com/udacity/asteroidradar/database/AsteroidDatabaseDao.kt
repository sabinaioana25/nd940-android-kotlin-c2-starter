package com.udacity.asteroidradar.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface AsteroidDatabaseDao {

    @Query("SELECT * FROM databaseasteroid")
    fun getAsteroids(): LiveData<List<DatabaseAsteroid>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg asteroids: DatabaseAsteroid)
}

@Database(entities = [DatabaseAsteroid::class], version = 1)
abstract class AsteroidsDatabase: RoomDatabase() {
    abstract val asteroidDatabaseDao: AsteroidDatabaseDao
}

private lateinit var INSTANCE: AsteroidsDatabase
fun getDatabase(context: Context): AsteroidsDatabase {
    synchronized(AsteroidsDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                    AsteroidsDatabase::class.java, "asteroids"
            ).build()
        }
        return INSTANCE
    }
}

