package com.example.healthtrackerpraksa.persistence

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.healthtrackerpraksa.persistence.model.BloodPressure
import com.example.healthtrackerpraksa.persistence.model.BloodSugar
import com.example.healthtrackerpraksa.persistence.model.Temperature

@Dao
interface IHealthStatusDao {

    @Query("Select * from temp_table")
    fun getAllTemperatures(): LiveData<List<Temperature>>

    @Query("Select * from blood_pressure_table")
    fun getAllBloodPressure(): LiveData<List<BloodPressure>>

    @Query("Select * from blood_sugar_table")
    fun getAllBloodSugar(): LiveData<List<BloodSugar>>

    @Insert
    suspend fun insertTemperature(temperature: Temperature)

    @Insert
    suspend fun insertBloodPressure(bloodPressure: BloodPressure)

    @Insert
    suspend fun insertBloodSugar(bloodSugar: BloodSugar)
}