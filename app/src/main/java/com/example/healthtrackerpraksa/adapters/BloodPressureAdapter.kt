package com.example.healthtrackerpraksa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.persistence.model.BloodPressure

class BloodPressureHolder(view: View): RecyclerView.ViewHolder(view) {
    val upperValue = view.findViewById<TextView>(R.id.tv_upper_item_blood_pressure)
    val lowerValue = view.findViewById<TextView>(R.id.tv_lower_item_blood_pressure)
    val dateTime = view.findViewById<TextView>(R.id.tv_date_item_blood_pressure)
}
class BloodPressureAdapter(private val items: List<BloodPressure>, private val context: Context): RecyclerView.Adapter<BloodPressureHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodPressureHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.blood_pressure_item, parent, false)
        return BloodPressureHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BloodPressureHolder, position: Int) {
        val item = items[position]
        with(holder) {
            upperValue.text = item.upperValue.toString()
            lowerValue.text = item.lowerValue.toString()
            dateTime.text = item.measureTime
        }
    }
}