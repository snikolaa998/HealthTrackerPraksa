package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.adapters.BloodPressureAdapter
import com.example.healthtrackerpraksa.adapters.CalendarAdapter
import com.example.healthtrackerpraksa.repository.Repository
import com.example.healthtrackerpraksa.viewModels.BloodPressureViewModel
import com.example.healthtrackerpraksa.viewModels.BloodPressureViewModelFactory
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarFragment : Fragment() {

    private val dayList = ArrayList<Int>()
    private val dates = ArrayList<Date>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var bloodPressureViewModel: BloodPressureViewModel
    private lateinit var bloodPressureViewModelFactory: BloodPressureViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_calendar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = view.findViewById(R.id.recyclerViewCalendar)
        val repo = Repository(activity?.application!!)
        var currentMonth = 11
        val btnNext = view.findViewById<Button>(R.id.btn_next_month)
        val btnPrevious = view.findViewById<Button>(R.id.btn_previous_month)
        val showYearMonth = view.findViewById<TextView>(R.id.tv_date_and_year_calendar)

        bloodPressureViewModelFactory = BloodPressureViewModelFactory(repo)
        bloodPressureViewModel = ViewModelProvider(this, bloodPressureViewModelFactory).get(BloodPressureViewModel::class.java)
        bloodPressureViewModel.getBloodPresure()
        bloodPressureViewModel.allBloodPressure.observe(requireActivity(), androidx.lifecycle.Observer {
            for(value in it) {
                dates.add(value.measureTime)
            }
        })

        showCalendar(showYearMonth, currentMonth)

        btnNext.setOnClickListener {
            currentMonth += 1
            dates.clear()
            dayList.clear()
            bloodPressureViewModel.getBloodPresure()
            bloodPressureViewModel.allBloodPressure.observe(requireActivity(), androidx.lifecycle.Observer {
                for(value in it) {
                    dates.add(value.measureTime)
                }
            })
            showCalendar(showYearMonth, currentMonth)
        }

        btnPrevious.setOnClickListener {
            currentMonth -= 1
            dayList.clear()
            dates.clear()
            bloodPressureViewModel.getBloodPresure()
            bloodPressureViewModel.allBloodPressure.observe(requireActivity(), androidx.lifecycle.Observer {
                for(value in it) {
                    dates.add(value.measureTime)
                }

            })
            showCalendar(showYearMonth, currentMonth)
        }

    }

    private fun showCalendar(showDateYear: TextView, mCurrentMonth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        calendar.set(Calendar.MONTH, mCurrentMonth)
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        for (i in 0 until dayOfWeek - 1) {
            dayList.add(-1)
        }
        val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        for (i in 0 until daysInMonth) {
            dayList.add(i + 1)
        }
        for (i in dayList.size until 42) {
            dayList.add(-1)
        }
        val adapter = CalendarAdapter(dayList, requireActivity(), dates, mCurrentMonth)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(requireActivity(), 7)
        val simpleDateFormat = SimpleDateFormat("MMMM yyyy", Locale.getDefault())
        val date = simpleDateFormat.format(calendar.time)
        showDateYear.text = date.toString()
    }
}