package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.models.Temperature
import com.example.healthtrackerpraksa.repositories.HealthTrackerRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class TemperatureViewModel() : ViewModel() {

    private val healthTrackerRepository = HealthTrackerRepository()

    private val _temperatureLiveData: MutableLiveData<List<Temperature>> = MutableLiveData()
    val temperatureLiveData: LiveData<List<Temperature>>
        get() = _temperatureLiveData


    fun initTemperatureLiveData() {
        healthTrackerRepository.getAllTemperatures().map {
            _temperatureLiveData.value = it
        }.launchIn(viewModelScope)
    }
}