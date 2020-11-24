package com.example.healthtrackerpraksa.viewModels

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.persistence.model.BloodPressure
import com.example.healthtrackerpraksa.repository.Repository
import kotlinx.coroutines.launch

class BloodPressureViewModel(private val repository: Repository) : ViewModel() {

    val allBloodPressure: LiveData<List<BloodPressure>> = repository.getBloodPressure()

    fun insert(bloodPressure: BloodPressure) {
        viewModelScope.launch {
            repository.insert(bloodPressure)
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