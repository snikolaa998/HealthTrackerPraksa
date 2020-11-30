package com.example.healthtrackerpraksa.ui.fragments

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.healthtrackerpraksa.R
import java.time.LocalDate


class CalendarFragment : Fragment(R.layout.fragment_calendar) {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = GridLayoutManager(this.context, 7)
//        rv_calendar.layoutManager = manager
//        rv_calendar.adapter = CalendarAdapter()

        val date = LocalDate.of(2020,11,1)

        Log.i("DATE_TEST", "onViewCreated: ${date.dayOfWeek.value}")


    }

}