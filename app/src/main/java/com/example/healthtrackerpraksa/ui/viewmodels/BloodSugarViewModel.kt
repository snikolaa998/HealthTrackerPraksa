package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.repositories.HealthTrackerRepository
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map

class BloodSugarViewModel : ViewModel() {

    private val healthTrackerRepository = HealthTrackerRepository()

    private val _bloodSugarLiveData = MutableLiveData<List<BloodSugar>>()
    val bloodSugarLiveData: LiveData<List<BloodSugar>>
        get() = _bloodSugarLiveData

    fun initBloodSugarLiveData() {
        healthTrackerRepository.getAllBloodSugar().map {
            _bloodSugarLiveData.value = it
        }.launchIn(viewModelScope)
    }
}