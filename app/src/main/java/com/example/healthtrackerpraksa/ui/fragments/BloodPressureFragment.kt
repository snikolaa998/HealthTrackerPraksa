package com.example.healthtrackerpraksa.ui.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.interfaces.DataIsReady
import com.example.healthtrackerpraksa.persistence.model.BloodPressure
import com.example.healthtrackerpraksa.ui.BloodPressurePopUp
import com.example.healthtrackerpraksa.ui.MainActivity
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class BloodPressureFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_pressure, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(dateTime)
        val date = SimpleDateFormat("dd MM", Locale.getDefault()).format(dateTime)
        Toast.makeText(activity, "Vreme je $time, datum je: $date", Toast.LENGTH_SHORT).show()
    }
}