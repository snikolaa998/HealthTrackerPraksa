package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.repositories.HealthTrackerRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class BloodPressureViewModel : ViewModel() {

    private val healthTrackerRepository = HealthTrackerRepository()

    private var _bloodPressureLiveData = MutableLiveData<List<BloodPressure>>()
    val bloodPressureLiveData: LiveData<List<BloodPressure>>
        get() = _bloodPressureLiveData

    fun initBloodPressureLiveData() {
        healthTrackerRepository.getAllBloodPressures().map {
            _bloodPressureLiveData.value = it
        }.launchIn(viewModelScope)
    }
}