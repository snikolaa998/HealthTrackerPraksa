package com.example.healthtrackerpraksa

import android.app.Application
import com.example.healthtrackerpraksa.persistence.HealthTrackerDb
import com.example.healthtrackerpraksa.repository.MainRepository

class MyApplication : Application() {

    private val dataBase by lazy {
        HealthTrackerDb.getDatabase(this)
    }

    val repository by lazy {
        MainRepository(dataBase!!.healthStatusDao())
    }


}