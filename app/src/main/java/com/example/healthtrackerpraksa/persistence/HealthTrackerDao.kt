package com.example.healthtrackerpraksa.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthtrackerpraksa.model.Temperature
import java.util.*

@Dao
interface HealthTrackerDao {

    @Query("Select * from temperature_table order by timeWhenMeasured desc")
    fun getAllTemperatures(): LiveData<List<Temperature>>
//
//    @Query("Select * from blood_pressure_table")
//    fun getAllBloodPressure(): LiveData<List<BloodPressure>>
//
//    @Query("Select * from blood_sugar_table")
//    fun getAllBloodSugar(): LiveData<List<BloodSugar>>

    @Query("Select * from temperature_table where timeWhenMeasured between :date1 and :date2")
     suspend fun getSpecificDates(date1: Date, date2: Date): List<Temperature>


    @Insert
    suspend fun insertTemperature(temperature: Temperature)
//
//    @Insert
//    suspend fun insertBloodPressure(bloodPressure: BloodPressure)
//
//    @Insert
//    suspend fun insertBloodSugar(bloodSugar: BloodSugar)
}