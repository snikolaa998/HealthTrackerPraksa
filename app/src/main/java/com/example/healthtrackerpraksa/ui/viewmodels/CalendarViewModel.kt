package com.example.healthtrackerpraksa.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthtrackerpraksa.models.BloodPressure
import com.example.healthtrackerpraksa.models.BloodSugar
import com.example.healthtrackerpraksa.models.Temperature
import com.example.healthtrackerpraksa.repositories.HealthTrackerRepository
import com.example.healthtrackerpraksa.models.InputHistory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import java.util.*

class CalendarViewModel : ViewModel() {

    private val healthTrackerRepository = HealthTrackerRepository()
    private val calendarMin = Calendar.getInstance()
    private val calendarMax = Calendar.getInstance()
    private var inputHistory = InputHistory(listOf(), listOf(), listOf())


    private val _inputHistoryLiveData = MutableLiveData<InputHistory>()
    val inputHistoryLiveData: LiveData<InputHistory>
        get() = _inputHistoryLiveData

    init {
        calendarMin.set(Calendar.DAY_OF_MONTH, 1)
        calendarMax.set(Calendar.DAY_OF_MONTH, calendarMax.getActualMaximum(Calendar.DAY_OF_MONTH))

        getInputHistory(calendarMin.time, calendarMax.time)
    }

    fun getInputHistory(dateMin: Date, dateMax: Date) {
        getTemperatureMonthInputHistory(dateMin, dateMax)
            .map {
                inputHistory.temperatureInputHistory = it
                _inputHistoryLiveData.postValue(inputHistory)
            }.launchIn(viewModelScope)
        getBloodSugarMonthInputHistory(dateMin, dateMax)
            .map {
                inputHistory.bloodSugarInputHistory = it
                _inputHistoryLiveData.postValue(inputHistory)
            }.launchIn(viewModelScope)
        getBloodPressureMonthInputHistory(dateMin, dateMax)
            .map {
                inputHistory.bloodPressureInputHistory = it
                _inputHistoryLiveData.postValue(inputHistory)
            }.launchIn(viewModelScope)

    }

    private fun getTemperatureMonthInputHistory(
        dateMin: Date,
        dateMax: Date
    ): Flow<List<Temperature>> {
        return healthTrackerRepository.getTemperatureInputForMonth(dateMin, dateMax)
    }

    private fun getBloodSugarMonthInputHistory(
        dateMin: Date,
        dateMax: Date
    ): Flow<List<BloodSugar>> {
        return healthTrackerRepository.getBloodSugarInputForMonth(dateMin, dateMax)
    }

    private fun getBloodPressureMonthInputHistory(
        dateMin: Date,
        dateMax: Date
    ): Flow<List<BloodPressure>> {
        return healthTrackerRepository.getBloodPressureInputForMonth(dateMin, dateMax)
    }
}