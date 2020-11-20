package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.repository.MainRepository
import kotlinx.coroutines.launch
import java.lang.IllegalArgumentException

class MainViewModel(private val repository: MainRepository) : ViewModel() {


    fun insertTemperature(temperature: Temperature) {
        viewModelScope.launch {
            repository.insertTemperature(temperature)
        }
    }



}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class")
    }

}