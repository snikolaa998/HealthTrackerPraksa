package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.ui.viewmodels.CalendarViewModel
import com.example.healthtrackerpraksa.models.InputHistory
import com.example.healthtrackerpraksa.ui.customcomponents.uicomponents.calendarcomponent.IOnChangeMonthButtonClickedListener
import com.example.healthtrackerpraksa.ui.customcomponents.uicomponents.calendarcomponent.MyCustomCalendarComponent
import com.example.healthtrackerpraksa.ui.customcomponents.uicomponents.calendarcomponent.OnRecyclerItemClickListener
import kotlinx.android.synthetic.main.fragment_calendar.*
import java.util.*

class CalendarFragment : Fragment(R.layout.fragment_calendar), IOnChangeMonthButtonClickedListener {

    private val calendarViewModel: CalendarViewModel by viewModels()
    lateinit var calendarComponent: MyCustomCalendarComponent
    private lateinit var inputHistory: InputHistory
    val calendar = Calendar.getInstance()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        calendarComponent = view.findViewById(R.id.cc_calendar)
        calendarComponent.setOnChangeMonthButtonClicked(this)
        calendarComponent.calendarAdapter.setOnRecyclerClickListener(object :
            OnRecyclerItemClickListener {
            override fun onRecyclerItemClicked(dayOfMonth: Int) {
                if (getIndexOfFirstTemp(dayOfMonth) != -1)
                    tv_current_status_temperature.text =
                        inputHistory.temperatureInputHistory[getIndexOfFirstTemp(dayOfMonth)].temperatureValue
                else tv_current_status_temperature.text = "Not measured for day"

                if (getIndexOfFirstBloodSugar(dayOfMonth) != -1) {
                    tv_current_status_blood_sugar.text =
                        inputHistory.bloodSugarInputHistory[getIndexOfFirstBloodSugar(dayOfMonth)].value
                } else tv_current_status_blood_sugar.text = "Not measured for day"

                if (getIndexOfFirstTempBloodPressure(dayOfMonth) != -1) {
                    tv_current_status_blood_pressure.text =
                        inputHistory.bloodPressureInputHistory[getIndexOfFirstTempBloodPressure(
                            dayOfMonth
                        )].valueUpper + " " + inputHistory.bloodPressureInputHistory[getIndexOfFirstTempBloodPressure(
                            dayOfMonth
                        )].valueLower
                } else tv_current_status_blood_pressure.text = "Not measured for day"
            }
        })

        subscribeObserver()
    }

    private fun getIndexOfFirstTemp(dayOfMonth: Int): Int {
        return inputHistory.temperatureInputHistory.indexOfFirst {
            calendar.time = it.timeWhenMeasured
            dayOfMonth == calendar.get(Calendar.DAY_OF_MONTH)
        }
    }

    private fun getIndexOfFirstBloodSugar(dayOfMonth: Int): Int {
        return inputHistory.bloodSugarInputHistory.indexOfFirst {
            calendar.time = it.timeWhenMeasured
            dayOfMonth == calendar.get(Calendar.DAY_OF_MONTH)
        }
    }

    private fun getIndexOfFirstTempBloodPressure(dayOfMonth: Int): Int {
        return inputHistory.bloodPressureInputHistory.indexOfFirst {
            calendar.time = it.timeWhenMeasured
            dayOfMonth == calendar.get(Calendar.DAY_OF_MONTH)
        }
    }


    private fun subscribeObserver() {
        calendarViewModel.inputHistoryLiveData.observe(
            viewLifecycleOwner,
            { inputHistory ->
                this.inputHistory = inputHistory
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
