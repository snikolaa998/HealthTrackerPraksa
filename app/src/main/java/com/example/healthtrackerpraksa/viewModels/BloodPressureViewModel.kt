package com.example.healthtrackerpraksa.viewModels

import androidx.lifecycle.*
import com.example.healthtrackerpraksa.persistence.model.BloodPressure
import com.example.healthtrackerpraksa.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BloodPressureViewModel(private val repository: Repository) : ViewModel() {

    val allBloodPressure = MutableLiveData<List<BloodPressure>>()


    fun getBloodPresure(){
        viewModelScope.launch(Dispatchers.IO) {
            allBloodPressure.postValue(repository.getBloodPressure())
        }
    }
    fun getBloodPresureForCurrentMonth(currentMonth: Int){
        viewModelScope.launch(Dispatchers.IO) {
            allBloodPressure.postValue(repository.getBloodPressure())
        }
    }
    fun insert(bloodPressure: BloodPressure) {
        viewModelScope.launch {
            repository.insert(bloodPressure)
            getBloodPresure()
        }
    }
}

class BloodPressureViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BloodPressureViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BloodPressureViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}