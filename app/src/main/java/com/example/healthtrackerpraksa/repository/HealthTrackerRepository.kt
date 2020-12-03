package com.example.healthtrackerpraksa.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.healthtrackerpraksa.MyApplication
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.persistence.HealthTrackerDb
import java.util.*

class HealthTrackerRepository() {

    private val healthTrackerDb = HealthTrackerDb.getDatabase()

    private val healthTrackerDao = healthTrackerDb.healthStatusDao()

    fun getAllTemperatures(): LiveData<List<Temperature>> {
        return healthTrackerDao.getAllTemperatures()
    }

    suspend fun insertTemperature(temperature: Temperature) {
        healthTrackerDao.insertTemperature(temperature)
    }

    suspend fun getSpecificTemperatures(dateMin: Date, dateMax: Date): List<Temperature> {
        return healthTrackerDao.getSpecificDates(dateMin, dateMax)
    }


}