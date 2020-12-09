package com.example.healthtrackerpraksa.persistence.typeconverters

import androidx.room.TypeConverter
import java.util.*

class TimeConverters {

    @TypeConverter
    fun fromDateToMillis(dateAndTime: Date): Long = dateAndTime.time

    @TypeConverter
    fun fromMillisToDate(dateInMillis: Long): Date = Date(dateInMillis)
}