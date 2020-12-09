package com.example.healthtrackerpraksa.persistence

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.healthtrackerpraksa.MyApplication
import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.persistence.typeconverters.TimeConverters
import com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent.HEALTH_TRACKER_DATABASE_NAME

@Database(
    entities = [BloodPressure::class, BloodSugar::class, Temperature::class],
    version = 1, exportSchema = false
)
@TypeConverters(TimeConverters::class)
abstract class HealthTrackerDb : RoomDatabase() {

    abstract fun healthStatusDao(): HealthTrackerDao

    companion object {
        @Volatile
        private var INSTANCE: HealthTrackerDb? = null

        fun getDatabase(): HealthTrackerDb {
            if (INSTANCE == null) {
                synchronized(this) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            MyApplication.instance,
                            HealthTrackerDb::class.java,
                            HEALTH_TRACKER_DATABASE_NAME
                        )
                            .build()

                    }
                }

            }
            return INSTANCE!!
        }
    }
}
