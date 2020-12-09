package com.example.healthtrackerpraksa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "temperature_table")
data class Temperature(
    var temperatureValue: String,
    var timeWhenMeasured: Date,
    var note: String,
    var unitOfMeasure: Char
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

@Entity(tableName = "blood_pressure_table")
data class BloodPressure(

    val valueUpper: String,
    val valueLower: String,
    val timeWhenMeasured: Date,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

@Entity(tableName = "blood_sugar_table")
data class BloodSugar(
    val value: String,
    val timeWhenMeasured: Date,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

