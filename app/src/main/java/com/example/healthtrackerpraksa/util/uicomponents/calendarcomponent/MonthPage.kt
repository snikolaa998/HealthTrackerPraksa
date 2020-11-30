package com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent

import java.util.*


class MonthPage() {

    private val calendar: Calendar = Calendar.getInstance()
    private var numOfDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val listOfDays = mutableListOf<DayOfTheWeek>()


    private fun populateListOfDays() {
        calendar.set(Calendar.DAY_OF_MONTH, 1)
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

    fun previousMonth() {
        listOfDays.clear()
        calendar.add(Calendar.MONTH, -1)
        populateListOfDays()
    }

    fun nextMonth() {
        listOfDays.clear()
        calendar.add(Calendar.MONTH, 1)
        populateListOfDays()
    }

    fun getMonth (): CharSequence {

        return "${calendar.get(Calendar.MONTH)}, ${calendar.get(Calendar.YEAR)}"
    }

    init {
        populateListOfDays()
    }
}