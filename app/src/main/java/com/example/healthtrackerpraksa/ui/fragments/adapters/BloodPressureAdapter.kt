package com.example.healthtrackerpraksa.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.models.BloodPressure
import com.example.healthtrackerpraksa.util.FormatDate


class BloodPressureAdapter(private val bloodPressureData: List<BloodPressure>) :
    RecyclerView.Adapter<BloodPressureViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodPressureViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_blood_pressure, parent, false)
        return BloodPressureViewHolder(view)
    }

    override fun onBindViewHolder(holder: BloodPressureViewHolder, position: Int) {
        val bloodPressure = bloodPressureData[position]
        holder.bloodPressureUpper.text = bloodPressure.valueUpper
        holder.bloodPressureLower.text = bloodPressure.valueLower
        holder.timeWhenMeasured.text = FormatDate.formatDate(bloodPressure.timeWhenMeasured)
    }

    override fun getItemCount(): Int {
        return bloodPressureData.size
    }
}

class BloodPressureViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val bloodPressureUpper: TextView = view.findViewById(R.id.tv_blood_pressure_upper)
    val bloodPressureLower: TextView = view.findViewById(R.id.tv_blood_pressure_lower)
    val timeWhenMeasured: TextView = view.findViewById(R.id.tv_blood_pressure_time_when_measured)

}
