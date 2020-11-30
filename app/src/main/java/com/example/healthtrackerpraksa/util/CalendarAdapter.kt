package com.example.healthtrackerpraksa.util.uicomponents

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.util.MonthPage

class CalendarAdapter : RecyclerView.Adapter<CalendarViewHolder>() {

    val testPage = MonthPage()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_calendar, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val day = testPage.listOfDays[position]
        holder.calendar.text = day.dayOfTheMonth.toString()
    }

    override fun getItemCount(): Int {
        return 42
    }
}

class CalendarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val calendar: TextView = view.findViewById(R.id.tv_calendar_date)

    init {
        calendar.setOnClickListener {

        }
    }

}
