package com.example.healthtrackerpraksa.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.healthtrackerpraksa.persistence.HealthTrackerDb
import com.example.healthtrackerpraksa.persistence.IHealthStatusDao
import com.example.healthtrackerpraksa.persistence.model.BloodPressure
import com.example.healthtrackerpraksa.persistence.model.BloodSugar

class Repository(application: Application) {
    private var healthStatusDao: IHealthStatusDao?

    init {
        val db = HealthTrackerDb.getDatabase(application)
        healthStatusDao = db?.healthStatusDao()
    }

    @Suppress
    @WorkerThread
    suspend fun insert(bloodPressure: com.example.healthtrackerpraksa.persistence.model.BloodPressure) {
        healthStatusDao?.insertBloodPressure(bloodPressure)
    }

    suspend fun getBloodPressure(): List<BloodPressure>{
        return healthStatusDao?.getAllBloodPressure()!!
    }

    suspend fun getBloodSugar(): List<BloodSugar> {
        return healthStatusDao?.getAllBloodSugar()!!
    }

    @Suppress
    @WorkerThread
    suspend fun insert(bloodSugar: BloodSugar) {
        healthStatusDao?.insertBloodSugar(bloodSugar)
    }
}