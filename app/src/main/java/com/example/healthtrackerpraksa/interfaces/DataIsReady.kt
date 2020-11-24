package com.example.healthtrackerpraksa.interfaces

import com.example.healthtrackerpraksa.persistence.model.BloodPressure

interface DataIsReady {
    fun dataIsReady(bloodPressure: BloodPressure)
}