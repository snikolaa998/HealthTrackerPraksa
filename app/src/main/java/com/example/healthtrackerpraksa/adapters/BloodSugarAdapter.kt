package com.example.healthtrackerpraksa.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.persistence.model.BloodSugar
import java.text.SimpleDateFormat

class BloodSugarHolder(view: View) : RecyclerView.ViewHolder(view) {
    val value = view.findViewById<TextView>(R.id.tv_current_value_sugar_item)
    val date = view.findViewById<TextView>(R.id.tv_current_date_sugar_item)
}

class BloodSugarAdapter(private val items: List<BloodSugar>, private val context: Context) : RecyclerView.Adapter<BloodSugarHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodSugarHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.blood_sugar_item, parent, false)
        return BloodSugarHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BloodSugarHolder, position: Int) {
        val item = items[position]
        with(holder) {
            value.text = item.value.toString()
            val simpleDateFormat = SimpleDateFormat("MMMM dd YYYY")
            date.text = simpleDateFormat.format(item.measureTime)
        }
    }
}