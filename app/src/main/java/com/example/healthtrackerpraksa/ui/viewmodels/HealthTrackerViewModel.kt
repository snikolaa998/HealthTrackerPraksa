package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.*
import com.example.healthtrackerpraksa.models.BloodPressure
import com.example.healthtrackerpraksa.models.BloodSugar
import com.example.healthtrackerpraksa.models.Temperature
import com.example.healthtrackerpraksa.repositories.HealthTrackerRepository
import kotlinx.coroutines.launch

class HealthTrackerViewModel() : ViewModel() {

    private val repository = HealthTrackerRepository()

    // Temperature
    fun insertTemperature(temperature: Temperature) {
        viewModelScope.launch {
            repository.insertTemperature(temperature)
        }
    }

    //Blood Sugar
    fun insertBloodSugar(bloodSugar: BloodSugar) {
        viewModelScope.launch {
            repository.insertBloodSugar(bloodSugar)
        }
    }

    //Blood Pressure
    fun insertBloodPressure(bloodPressure: BloodPressure) {
        viewModelScope.launch {
            repository.insertBloodPressure(bloodPressure)
        }
    }


}


