package com.example.healthtrackerpraksa.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.util.FormatDate
import kotlinx.android.synthetic.main.card_temperature.view.*
import java.util.*

class TemperatureAdapter(private val temperatureList: List<Temperature>) :
    RecyclerView.Adapter<TemperatureViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TemperatureViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_temperature, parent, false)
        return TemperatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: TemperatureViewHolder, position: Int) {
        val temperature = temperatureList[position]
        holder.temperature.text = temperature.temperatureValue
        holder.timeTaken.text = FormatDate.formatDate(temperature.timeWhenMeasured)

    }

    override fun getItemCount(): Int {
        return temperatureList.size
    }
}

class TemperatureViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val temperature: TextView = view.findViewById(R.id.tv_temperature)
    val timeTaken: TextView = view.findViewById(R.id.tv_time_taken)

    init {
        view.setOnClickListener {
            Toast.makeText(view.context, "Clicked!", Toast.LENGTH_SHORT).show()
        }
    }
}
