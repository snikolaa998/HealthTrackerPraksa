package com.example.healthtrackerpraksa.repository

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.persistence.HealthTrackerDb
import com.example.healthtrackerpraksa.persistence.IHealthStatusDao

class MainRepository (private val healthStatusDao: IHealthStatusDao){


    fun getAllTemperature(): LiveData<List<Temperature>> {
        return healthStatusDao.getAllTemperatures()
    }

    suspend fun insertTemperature(temperature: Temperature) {
        healthStatusDao.insertTemperature(temperature)
    }


}