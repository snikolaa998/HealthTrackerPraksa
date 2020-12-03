package com.example.healthtrackerpraksa.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import java.util.*
import kotlin.collections.ArrayList

class CalendarHolder(view: View) : RecyclerView.ViewHolder(view) {
    val calendarItem = view.findViewById<TextView>(R.id.tv_calendar_item)
    val imageCalendarItem = view.findViewById<ImageView>(R.id.iv_calendar_item)
}

class CalendarAdapter(private val numberList: ArrayList<Int>, private val context: Context, private val datesList: ArrayList<Date>, private val currentMonth: Int) : RecyclerView.Adapter<CalendarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.calendar_item, parent, false)
        return CalendarHolder(view)
    }

    override fun getItemCount(): Int {
        return 42
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: CalendarHolder, position: Int) {
        val calendar = Calendar.getInstance()
        with(holder) {
            if (numberList[position] == -1) {
                calendarItem.text = ""
            } else {
                for (value in datesList) {
                    if (numberList[position] == value.date && currentMonth == value.month) {
                        imageCalendarItem.setImageResource(R.drawable.blood_presure_small)
                        calendarItem.text = numberList[position].toString()
                        calendarItem.setTextColor(Color.parseColor("#000000"))
                        calendarItem.typeface = Typeface.DEFAULT_BOLD
                        break
                    } else {
                        imageCalendarItem.setImageResource(0)
                        calendarItem.text = numberList[position].toString()
                    }
                }

            }
        }
    }
}