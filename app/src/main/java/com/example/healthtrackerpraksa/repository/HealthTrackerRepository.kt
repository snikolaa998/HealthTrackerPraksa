package com.example.healthtrackerpraksa.repository

import androidx.lifecycle.LiveData
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.persistence.HealthTrackerDb
import java.util.*

class HealthTrackerRepository() {

    private val healthTrackerDb = HealthTrackerDb.getDatabase()

    private val healthTrackerDao = healthTrackerDb.healthStatusDao()


    //Temperature
    fun getAllTemperatures(): LiveData<List<Temperature>> {
        return healthTrackerDao.getAllTemperatures()
    }

    suspend fun insertTemperature(temperature: Temperature) {
        healthTrackerDao.insertTemperature(temperature)
    }

    suspend fun getSpecificTemperatureDates(dateMin: Date, dateMax: Date): List<Temperature> {
        return healthTrackerDao.getSpecificTemperatureDates(dateMin, dateMax)
    }

    //Blood Sugar

    suspend fun insertBloodSugar(bloodSugar: BloodSugar) {
        healthTrackerDao.insertBloodSugar(bloodSugar)
    }

    fun getAllBloodSugar(): LiveData<List<BloodSugar>> {
        return healthTrackerDao.getAllBloodSugar()
    }

    suspend fun getSpecificBloodSugarDates(dateMin: Date, dateMax: Date): List<BloodSugar> {
        return healthTrackerDao.getSpecificBloodSugarDates(dateMin, dateMax)
    }


}