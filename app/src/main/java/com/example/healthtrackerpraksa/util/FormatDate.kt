package com.example.healthtrackerpraksa.util

import java.text.SimpleDateFormat
import java.util.*

object FormatDate {

    fun formatDate(date: Date): String {
        return SimpleDateFormat("EEE, MMM d, ''yy").format(date)
    }
}