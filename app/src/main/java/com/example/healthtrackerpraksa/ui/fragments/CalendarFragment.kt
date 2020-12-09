package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.ui.viewmodels.CalendarViewModel
import com.example.healthtrackerpraksa.ui.viewmodels.helpers.InputHistory
import com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent.IOnChangeMonthButtonClickedListener
import com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent.MyCustomCalendarComponent
import java.util.*

class CalendarFragment : Fragment(R.layout.fragment_calendar), IOnChangeMonthButtonClickedListener {

    private val calendarViewModel: CalendarViewModel by viewModels()
    lateinit var calendarComponent: MyCustomCalendarComponent

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarComponent = view.findViewById(R.id.cc_calendar)
        calendarComponent.setOnPreviousButtonClicked(this)

        subscribeObserver()
    }

    private fun subscribeObserver() {
        calendarViewModel.inputHistoryLiveData.observe(
            viewLifecycleOwner,
            { inputHistory ->
                calendarComponent.updateData(
                    inputHistory!!.temperatureInputHistory,
                    inputHistory.bloodSugarInputHistory,
                    inputHistory.bloodPressureInputHistory
                )
            })
    }

    override fun onChangeButtonClicked(calendar: Calendar) {
        val dateMin = Calendar.getInstance()
        val dateMax = Calendar.getInstance()
        dateMin.time = calendar.time
        dateMin.set(Calendar.DAY_OF_MONTH, 1)
        dateMax.time = calendar.time
        dateMax.set(Calendar.DAY_OF_MONTH, dateMax.getActualMaximum(Calendar.DAY_OF_MONTH))

        lifecycleScope.launchWhenCreated {
            calendarViewModel.getInputHistory(dateMin.time, dateMax.time)
        }

    }
}
