package com.example.healthtrackerpraksa.model

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "temperature_table")
data class Temperature(

    val temperatureValue: Int,
    val timeWhenMeasured: Int,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

@Entity(tableName = "blood_pressure_table")
data class BloodPressure(

    val value: Int,
    val measureTime: Int,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

@Entity(tableName = "blood_sugar_table")
data class BloodSugar(

    val value: Int,
    val measureTime: Int,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

