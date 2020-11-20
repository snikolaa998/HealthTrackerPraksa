package com.example.healthtrackerpraksa.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.ui.fragments.BloodPressure

@Database(
    entities = [BloodPressure::class, BloodSugar::class, Temperature::class],
    version = 1, exportSchema = false
)
abstract class HealthTrackerDb : RoomDatabase() {

    abstract fun healthStatusDao(): IHealthStatusDao

    companion object {
        @Volatile
        private var INSTANCE: HealthTrackerDb? = null

        fun getDatabase(context: Context): HealthTrackerDb {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            HealthTrackerDb::class.java,
                            "health_tracker_database"
                        )
                            .build()

                    }
                }

            }
            return INSTANCE!!
        }
    }
}
