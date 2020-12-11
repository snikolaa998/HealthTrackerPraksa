package com.example.healthtrackerpraksa.ui.fragments.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.models.BloodSugar
import com.example.healthtrackerpraksa.util.FormatDate

class BloodSugarAdapter(private val bloodSugarList: List<BloodSugar>) :
    RecyclerView.Adapter<BloodSugarViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BloodSugarViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_blood_sugar, parent, false)
        return BloodSugarViewHolder(view)
    }

    override fun onBindViewHolder(holder: BloodSugarViewHolder, position: Int) {
        val bloodSugar = bloodSugarList[position]
        holder.bloodSugar.text = bloodSugar.value
        holder.bloodSugarTimeWhenMeasured.text = FormatDate.formatDate(bloodSugar.timeWhenMeasured)
    }

    override fun getItemCount(): Int {
        return bloodSugarList.size
    }
}

class BloodSugarViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val bloodSugar: TextView = view.findViewById(R.id.tv_blood_sugar)
    val bloodSugarTimeWhenMeasured: TextView =
        view.findViewById(R.id.tv_blood_sugar_time_when_measured)

    init {

    }

}
