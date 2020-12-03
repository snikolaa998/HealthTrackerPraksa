package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.ui.viewmodels.HealthTrackerViewModel
import com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent.IOnChangeMonthButtonClickedListener
import com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent.MyCustomCalendarComponent
import kotlinx.coroutines.launch
import java.util.*

class CalendarFragment : Fragment(R.layout.fragment_calendar), IOnChangeMonthButtonClickedListener {

    private val viewModel: HealthTrackerViewModel by activityViewModels()
    private lateinit var calendar: Calendar

    lateinit var calendarComponent: MyCustomCalendarComponent
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarComponent = view.findViewById(R.id.cc_calendar)
        calendarComponent.setOnPreviousButtonClicked(this)

        val dateMin = Calendar.getInstance()
        dateMin.set(Calendar.DAY_OF_MONTH, 1)
        val dateMax = Calendar.getInstance()
        dateMax.set(Calendar.DAY_OF_MONTH, dateMax.getActualMaximum(Calendar.DAY_OF_MONTH))

        lifecycleScope.launch {
            val list = viewModel.getSpecificTemperatures(dateMin.time, dateMax.time)
            calendarComponent.updateData(list)
        }
    }

    override fun onChangeButtonClicked(calendar: Calendar) {
        val dateMin = Calendar.getInstance()
        dateMin.time = calendar.time
        dateMin.set(Calendar.DAY_OF_MONTH, 1)
        val dateMax = Calendar.getInstance()
        dateMax.time = calendar.time
        dateMax.set(Calendar.DAY_OF_MONTH, dateMax.getActualMaximum(Calendar.DAY_OF_MONTH))

        lifecycleScope.launch {
            val list = viewModel.getSpecificTemperatures(dateMin.time, dateMax.time)
            calendarComponent.updateData(list)
        }
    }
}
