package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.*
import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.model.BloodSugar
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

    suspend fun getSpecificTemperatures(dateMin: Date, dateMax: Date): List<Temperature> {
        return repository.getSpecificTemperatureDates(dateMin, dateMax)
    }


    fun insertBloodSugar(bloodSugar: BloodSugar) {
        viewModelScope.launch {
            repository.insertBloodSugar(bloodSugar)
        }
    }

    fun getAllBloodSugar(): LiveData<List<BloodSugar>> {
        return repository.getAllBloodSugar()
    }

    suspend fun getSpecificBloodSugar(dateMin: Date, dateMax: Date): List<BloodSugar> {
        return repository.getSpecificBloodSugarDates(dateMin, dateMax)
    }
}


