package com.example.healthtrackerpraksa.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.ui.viewmodels.helpers.InputHistory
import kotlinx.coroutines.flow.Flow
import java.util.*

@Dao
interface HealthTrackerDao {

    //Temperature
    @Query("Select * from temperature_table order by timeWhenMeasured desc")
    fun getAllTemperatures(): Flow<List<Temperature>>

    @Query("Select * from temperature_table where timeWhenMeasured between :date1 and :date2")
    fun getTemperatureInputForMonth(date1: Date, date2: Date): Flow<List<Temperature>>

    @Insert
    suspend fun insertTemperature(temperature: Temperature)

    //Blood Sugar
    @Query("Select * from blood_sugar_table order by timeWhenMeasured desc")
    fun getAllBloodSugar(): Flow<List<BloodSugar>>

    @Query("Select * from blood_sugar_table where timeWhenMeasured between :date1 and :date2")
    fun getBloodSugarInputForMonth(date1: Date, date2: Date): Flow<List<BloodSugar>>

    @Insert
    suspend fun insertBloodSugar(bloodSugar: BloodSugar)

    //Blood Pressure
    @Query("Select * from blood_pressure_table")
    fun getAllBloodPressure(): Flow<List<BloodPressure>>

    @Insert
    suspend fun insertBloodPressure(bloodPressure: BloodPressure)

    @Query("Select * from blood_pressure_table where timeWhenMeasured between :date1 and :date2")
    fun getBloodPressureInputForMonth(date1: Date, date2: Date): Flow<List<BloodPressure>>

}