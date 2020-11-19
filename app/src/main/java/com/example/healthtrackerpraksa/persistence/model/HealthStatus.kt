package com.example.healthtrackerpraksa.persistence.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "temp_table")
data class Temperature(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val value: Int,
    val measureTime: Int,
    val note: String
)

@Entity(tableName = "blood_pressure_table")
data class BloodPressure(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val value: Int,
    val measureTime: Int,
    val note: String
)

@Entity(tableName = "blood_sugar_table")
data class BloodSugar(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val value: Int,
    val measureTime: Int,
    val note: String
)

