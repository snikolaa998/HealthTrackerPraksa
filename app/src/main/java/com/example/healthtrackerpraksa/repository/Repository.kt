package com.example.healthtrackerpraksa.repository

import android.app.Application
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.example.healthtrackerpraksa.persistence.HealthTrackerDb
import com.example.healthtrackerpraksa.persistence.IHealthStatusDao
import com.example.healthtrackerpraksa.persistence.model.BloodPressure

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

    fun getBloodPressure(): LiveData<List<BloodPressure>>{
        return healthStatusDao?.getAllBloodPressure()!!
    }
}