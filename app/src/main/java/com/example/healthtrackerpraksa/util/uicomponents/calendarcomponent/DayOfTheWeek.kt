package com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent

import android.view.View


class DayOfTheWeek(
    val dayOfTheMonth: Int = 0,
    val day: String = "",
    var temperatureMeasured: Boolean = false,
    var bloodPressureMeasured: Boolean = false,
    var bloodSugarMeasured: Boolean = false
) {

    fun isTempVisible(): Int {
        return if (temperatureMeasured) View.VISIBLE else View.GONE
    }

    fun isBloodPressureVisible(): Int {
        return if (bloodPressureMeasured) View.VISIBLE else View.GONE
    }

    fun isBloodSugarVisible(): Int {
        return if (bloodSugarMeasured) View.VISIBLE else View.GONE
    }
}


