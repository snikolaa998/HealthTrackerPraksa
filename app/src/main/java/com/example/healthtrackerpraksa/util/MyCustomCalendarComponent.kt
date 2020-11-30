package com.example.healthtrackerpraksa.util

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.util.uicomponents.CalendarAdapter
import kotlinx.android.synthetic.main.my_custom_calendar.view.*

class MyCustomCalendarComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    var calendarAdapter = CalendarAdapter()

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.my_custom_calendar, this)
        rv_calendar_preview.layoutManager = GridLayoutManager(context, 7)
        rv_calendar_preview.adapter = calendarAdapter

        iv_arrow_previous.setOnClickListener {
            calendarAdapter.testPage.previousMonth()
            calendarAdapter.notifyDataSetChanged()
            tv_date_label.text = calendarAdapter.testPage.getMonth()

        }

        iv_arrow_next.setOnClickListener {
            calendarAdapter.testPage.nextMonth()
            calendarAdapter.notifyDataSetChanged()
            tv_date_label.text = calendarAdapter.testPage.getMonth()
        }

    }
}