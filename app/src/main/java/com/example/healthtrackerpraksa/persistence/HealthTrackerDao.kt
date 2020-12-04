package com.example.healthtrackerpraksa.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.model.Temperature
import java.util.*

@Dao
interface HealthTrackerDao {

    @Query("Select * from temperature_table order by timeWhenMeasured desc")
    fun getAllTemperatures(): LiveData<List<Temperature>>

    @Query("Select * from temperature_table where timeWhenMeasured between :date1 and :date2")
    suspend fun getSpecificTemperatureDates(date1: Date, date2: Date): List<Temperature>

    @Insert
    suspend fun insertTemperature(temperature: Temperature)


    @Query("Select * from blood_sugar_table order by timeWhenMeasured desc")
    fun getAllBloodSugar(): LiveData<List<BloodSugar>>

    @Query("Select * from blood_sugar_table where timeWhenMeasured between :date1 and :date2")
    suspend fun getSpecificBloodSugarDates(date1: Date, date2: Date): List<BloodSugar>

    @Insert
    suspend fun insertBloodSugar(bloodSugar: BloodSugar)


    @Query("Select * from blood_pressure_table")
    fun getAllBloodPressure(): LiveData<List<BloodPressure>>

    @Insert
    suspend fun insertBloodPressure(bloodPressure: BloodPressure)


}