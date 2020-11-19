package com.example.healthtrackerpraksa.persistence

import android.content.Context
import android.os.health.HealthStats
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthtrackerpraksa.persistence.model.BloodSugar
import com.example.healthtrackerpraksa.persistence.model.Temperature
import com.example.healthtrackerpraksa.ui.fragments.BloodPressure

@Database(
    entities = [BloodPressure::class, BloodSugar::class, Temperature::class],
    version = 1,
    exportSchema = false
)
abstract class HealthTrackerDb() : RoomDatabase() {

    abstract fun healthStatusDao(): IHealthStatusDao

    companion object {
        private var INSTANCE: HealthTrackerDb? = null

        internal fun getDatabase(context: Context): HealthTrackerDb? {
            if (INSTANCE == null) {
                synchronized(HealthTrackerDb::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            HealthTrackerDb::class.java,
                            "health_tracker_database"
                        )
                            .fallbackToDestructiveMigration()
                            .build()

                    }
                }
            }
            return INSTANCE
        }
    }

}