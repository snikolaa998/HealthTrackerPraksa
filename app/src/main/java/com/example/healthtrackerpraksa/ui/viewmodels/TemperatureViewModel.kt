package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.repository.HealthTrackerRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*

class TemperatureViewModel() : ViewModel() {

    private val healthTrackerRepository = HealthTrackerRepository()

    private val _temperatureLiveData: MutableLiveData<List<Temperature>> = MutableLiveData()
    val temperatureLiveData: LiveData<List<Temperature>>
        get() = _temperatureLiveData


    fun insertTemperature(temperature: Temperature) {
        viewModelScope.launch(IO) {
            healthTrackerRepository.insertTemperature(temperature)
        }
    }

    fun initTemperatureLiveData() {
        healthTrackerRepository.getAllTemperatures().map {
            _temperatureLiveData.value = it
        }.launchIn(viewModelScope)
    }
}