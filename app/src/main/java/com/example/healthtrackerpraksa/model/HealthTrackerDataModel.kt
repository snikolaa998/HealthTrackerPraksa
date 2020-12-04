package com.example.healthtrackerpraksa.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.healthtrackerpraksa.persistence.TimeConverters
import java.util.*


@Entity(tableName = "temperature_table")
data class Temperature(
    var temperatureValue: String,
    var timeWhenMeasured: Date,
    var note: String
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
    val value: String,
    val timeWhenMeasured: Date,
    val note: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null
}

