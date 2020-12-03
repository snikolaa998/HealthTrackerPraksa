package com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent

import android.util.Log
import com.example.healthtrackerpraksa.model.Temperature
import java.util.*


class MonthPage() {

    private val calendar: Calendar = Calendar.getInstance()
    private var numOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val listOfDays = mutableListOf<DayOfTheWeek>()
    private var temperatureInputHistory = listOf<Temperature>()

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
                    listOfDays.add(
                        daysListIndex - 1,
                        DayOfTheWeek(
                            dayInMonthCounter,
                            calendar.get(Calendar.DAY_OF_WEEK).toString()
                        )
                    )
                    dayInMonthCounter++
                } else {
                    listOfDays.add(daysListIndex - 1, DayOfTheWeek())
                }
            } else listOfDays.add(daysListIndex - 1, DayOfTheWeek())
        }
    }

    private fun populateAndUpdate() {
        populateListOfDays()
        updateListOfDays()
    }

    private fun updateListOfDays() {
        temperatureInputHistory.forEach {
            if (listOfDays.indexOfFirst { dayOfTheWeek ->
                    dayOfTheWeek.dayOfTheMonth == it.timeWhenMeasured.day
                } != -1) listOfDays[listOfDays.indexOfFirst { dayOfTheWeek ->
                dayOfTheWeek.dayOfTheMonth == it.timeWhenMeasured.day
            } - 1].temperatureMeasured = true
        }
    }

    fun previousMonth() {
        listOfDays.clear()
        calendar.add(Calendar.MONTH, -1)
    }

    fun nextMonth() {
        listOfDays.clear()
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

    fun updateData(temperatureInputHistory: List<Temperature>) {
        this.temperatureInputHistory = temperatureInputHistory
        populateAndUpdate()
    }
}