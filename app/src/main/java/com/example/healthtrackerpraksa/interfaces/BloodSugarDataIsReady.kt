package com.example.healthtrackerpraksa.interfaces

import com.example.healthtrackerpraksa.persistence.model.BloodSugar

interface BloodSugarDataIsReady {
    fun bloodSugarDataIsReady(bloodSugar: BloodSugar)
}