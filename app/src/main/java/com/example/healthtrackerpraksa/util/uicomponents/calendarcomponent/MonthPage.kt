package com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent

import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.model.Temperature
import java.util.*


class MonthPage() {

    private val calendar: Calendar = Calendar.getInstance()
    private var numOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    private val tempListOfDays = mutableListOf<DayOfTheWeek>()
    var listOfDays = mutableListOf<DayOfTheWeek>()

    private var temperatureInputHistory = listOf<Temperature>()
    private var bloodSugarInputHistory = listOf<BloodSugar>()
    private var bloodPressureInputHistory = listOf<BloodPressure>()

    private fun populateListOfDays() {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        numOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        var dayInMonthCounter = 1

        for (daysListIndex in 1 until 43) {
            if (dayInMonthCounter <= numOfDaysInMonth) {
                calendar.set(Calendar.DAY_OF_MONTH, dayInMonthCounter)

                if ((daysListIndex) % 7 == calendar.get(Calendar.DAY_OF_WEEK)
                    || (daysListIndex) % 7 == 0
                ) {
                    tempListOfDays.add(
                        daysListIndex - 1,
                        DayOfTheWeek(
                            dayInMonthCounter,
                            calendar.get(Calendar.DAY_OF_WEEK).toString()
                        )
                    )
                    dayInMonthCounter++
                } else {
                    tempListOfDays.add(daysListIndex - 1, DayOfTheWeek())
                }
            } else tempListOfDays.add(daysListIndex - 1, DayOfTheWeek())
        }
    }

    private fun populateAndUpdate() {
        tempListOfDays.clear()
        populateListOfDays()
        updateListOfDays()
    }

    private fun updateListOfDays() {
        val tempCalendar = Calendar.getInstance()
        temperatureInputHistory.forEach { temp ->
            tempCalendar.time = temp.timeWhenMeasured

            tempListOfDays[tempListOfDays.indexOfFirst {
                it.dayOfTheMonth == tempCalendar.get(Calendar.DAY_OF_MONTH)
            }].temperatureMeasured = true
        }

        bloodSugarInputHistory.forEach { bloodSugar ->
            tempCalendar.time = bloodSugar.timeWhenMeasured
            tempListOfDays[tempListOfDays.indexOfFirst { dayOfTheWeek ->
                dayOfTheWeek.dayOfTheMonth == tempCalendar.get(Calendar.DAY_OF_MONTH)
            }].bloodSugarMeasured = true
        }

        bloodPressureInputHistory.forEach { bloodPressure ->
            tempCalendar.time = bloodPressure.timeWhenMeasured
            tempListOfDays[tempListOfDays.indexOfFirst { dayOfTheWeek ->
                dayOfTheWeek.dayOfTheMonth == tempCalendar.get(Calendar.DAY_OF_MONTH)
            }].bloodPressureMeasured = true
        }
        listOfDays = tempListOfDays

    }

    fun previousMonth() {
        tempListOfDays.clear()
        calendar.add(Calendar.MONTH, -1)
    }

    fun nextMonth() {
        tempListOfDays.clear()
        calendar.add(Calendar.MONTH, 1)
    }

    fun getMonth(): Calendar {
        return calendar
    }

    fun getMonthNameInfo(): CharSequence {
        return "${getMonthName(calendar.get(Calendar.MONTH))}, ${calendar.get(Calendar.YEAR)}"
    }

    private fun getMonthName(month: Int): String {
        when (month) {
            Calendar.JANUARY -> return "JANUARY"
            Calendar.FEBRUARY -> return "FEBRUARY"
            Calendar.MARCH -> return "MARCH"
            Calendar.APRIL -> return "APRIL"
            Calendar.MAY -> return "MAY"
            Calendar.JUNE -> return "JUNE"
            Calendar.JULY -> return "JULY"
            Calendar.AUGUST -> return "AUGUST"
            Calendar.SEPTEMBER -> return "SEPTEMBER"
            Calendar.OCTOBER -> return "OCTOBER"
            Calendar.NOVEMBER -> return "NOVEMBER"
            Calendar.DECEMBER -> return "DECEMBER"
        }
        return ""
    }

    fun updateData(
        temperatureInputHistory: List<Temperature>,
        bloodSugarInputHistory: List<BloodSugar>,
        bloodPressureInputHistory: List<BloodPressure>
    ) {
        updateTempData(temperatureInputHistory)
        updateBloodSugarData(bloodSugarInputHistory)
        updateBloodPressureData(bloodPressureInputHistory)
        populateAndUpdate()
    }

    private fun updateBloodPressureData(bloodPressureInputHistory: List<BloodPressure>) {
        this.bloodPressureInputHistory = bloodPressureInputHistory
    }

    private fun updateTempData(temperatureInputHistory: List<Temperature>) {
        this.temperatureInputHistory = temperatureInputHistory
    }

    private fun updateBloodSugarData(bloodSugarInputHistory: List<BloodSugar>) {
        this.bloodSugarInputHistory = bloodSugarInputHistory

    }
}