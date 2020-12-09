package com.example.healthtrackerpraksa.repository

import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.persistence.HealthTrackerDb
import kotlinx.coroutines.flow.Flow
import java.util.*

class HealthTrackerRepository() {

    private val healthTrackerDb = HealthTrackerDb.getDatabase()
    private val healthTrackerDao = healthTrackerDb.healthStatusDao()


    //Temperature
    fun getAllTemperatures(): Flow<List<Temperature>> {
        return healthTrackerDao.getAllTemperatures()
    }

    suspend fun insertTemperature(temperature: Temperature) {
        healthTrackerDao.insertTemperature(temperature)
    }

    fun getTemperatureInputForMonth(dateMin: Date, dateMax: Date): Flow<List<Temperature>> {
        return healthTrackerDao.getTemperatureInputForMonth(dateMin, dateMax)
    }

    //Blood Sugar

    suspend fun insertBloodSugar(bloodSugar: BloodSugar) {
        healthTrackerDao.insertBloodSugar(bloodSugar)
    }

    fun getAllBloodSugar(): Flow<List<BloodSugar>> {
        return healthTrackerDao.getAllBloodSugar()
    }

    fun getBloodSugarInputForMonth(dateMin: Date, dateMax: Date): Flow<List<BloodSugar>> {
        return healthTrackerDao.getBloodSugarInputForMonth(dateMin, dateMax)
    }

    //Blood Pressure

    fun getAllBloodPressures(): Flow<List<BloodPressure>> {
        return healthTrackerDao.getAllBloodPressure()
    }

    suspend fun insertBloodPressure(bloodPressure: BloodPressure) {
        healthTrackerDao.insertBloodPressure(bloodPressure)
    }

    fun getBloodPressureInputForMonth(dateMin: Date, dateMax: Date): Flow<List<BloodPressure>> {
        return healthTrackerDao.getBloodPressureInputForMonth(dateMin, dateMax)
    }

}