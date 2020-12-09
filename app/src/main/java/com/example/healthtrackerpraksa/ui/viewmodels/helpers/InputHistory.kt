package com.example.healthtrackerpraksa.ui.viewmodels.helpers

import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.model.Temperature

class InputHistory(
    var temperatureInputHistory: List<Temperature>,
    var bloodSugarInputHistory: List<BloodSugar>,
    var bloodPressureInputHistory: List<BloodPressure>
)