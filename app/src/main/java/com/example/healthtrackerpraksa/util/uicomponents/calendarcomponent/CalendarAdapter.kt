package com.example.healthtrackerpraksa.util.uicomponents.calendarcomponent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import java.util.*

class CalendarAdapter(private val monthPage: MonthPage) :
    RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    private var onRecyclerItemClickListener: OnRecyclerItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_calendar, parent, false)
        return CalendarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        val day = monthPage.listOfDays[position]
        if (day.dayOfTheMonth != 0) {
            holder.calendar.text = day.dayOfTheMonth.toString()
            holder.temperatureIcon.visibility = day.isTempVisible()
            holder.bloodSugarIcon.visibility = day.isBloodSugarVisible()
            holder.bloodPressureIcon.visibility = day.isBloodPressureVisible()

        } else {
            holder.calendar.text = ""
        }
    }

    override fun getItemCount(): Int {
        return monthPage.listOfDays.size
    }

    fun setOnRecyclerClickListener(onRecyclerItemClickListener: OnRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener
    }

    inner class CalendarViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        val calendar: TextView = view.findViewById(R.id.tv_calendar_date)
        val temperatureIcon: ImageView = view.findViewById(R.id.iv_icon_temperature)
        val bloodPressureIcon: ImageView = view.findViewById(R.id.iv_icon_blood_pressure)
        val bloodSugarIcon: ImageView = view.findViewById(R.id.iv_icon_blood_sugar)

        init {
            view.setOnClickListener {
                this@CalendarAdapter.onRecyclerItemClickListener?.onRecyclerItemClicked(monthPage.listOfDays[this.adapterPosition].dayOfTheMonth)
            }
        }
    }
}


interface OnRecyclerItemClickListener {
    fun onRecyclerItemClicked(dayOfMonth: Int)
}