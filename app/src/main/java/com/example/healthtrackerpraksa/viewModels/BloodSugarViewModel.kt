package com.example.healthtrackerpraksa.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.persistence.model.BloodSugar
import com.example.healthtrackerpraksa.repository.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class BloodSugarViewModel(private val repository: Repository) : ViewModel() {
    val allBloodSugar = MutableLiveData<List<BloodSugar>>()

    fun getBloodSugar() {
        viewModelScope.launch(Dispatchers.IO) {
            allBloodSugar.postValue(repository.getBloodSugar())
        }
    }

    fun insert(bloodSugar: BloodSugar) {
        viewModelScope.launch {
            repository.insert(bloodSugar)
            getBloodSugar()
        }
    }
}

class BloodSugarViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BloodSugarViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BloodSugarViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}