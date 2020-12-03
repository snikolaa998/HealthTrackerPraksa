package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.repository.HealthTrackerRepository
import kotlinx.coroutines.launch
import java.util.*

class HealthTrackerViewModel() : ViewModel() {

    private val repository = HealthTrackerRepository()

    fun insertTemperature(temperature: Temperature) {
        viewModelScope.launch {
            repository.insertTemperature(temperature)
        }
    }

    fun getAllTemperatures(): LiveData<List<Temperature>> {
       return repository.getAllTemperatures()
    }

    suspend fun getSpecificTemperatures(dateMin: Date, dateMax: Date): List<Temperature>{
        return repository.getSpecificTemperatures(dateMin, dateMax)
    }

}


