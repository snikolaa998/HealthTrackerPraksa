package com.example.healthtrackerpraksa.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.interfaces.DataIsReady
import com.example.healthtrackerpraksa.persistence.model.BloodPressure
import java.text.SimpleDateFormat
import java.util.*

class BloodPressurePopUp(private val listener: DataIsReady) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_blood_pressure, container, false)
        val saveButton = view.findViewById<Button>(R.id.btn_save_blood_pressure)
        val cancelButton = view.findViewById<Button>(R.id.btn_cancel_blood_pressure)
        val etTime = view.findViewById<EditText>(R.id.et_time_blood_pressure)
        val etDate = view.findViewById<EditText>(R.id.et_date_blood_pressure)
        val upperValue = view.findViewById<EditText>(R.id.et_upper_blood_pressure)
        val lowerValue = view.findViewById<EditText>(R.id.et_lower_blood_pressure)
        val note = view.findViewById<EditText>(R.id.et_note_blood_pressure)
        val calendar = Calendar.getInstance()
        val dateTime = calendar.time
        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(dateTime)
        val date = SimpleDateFormat("dd/MM", Locale.getDefault()).format(dateTime)
        etTime.setText(time)
        etDate.setText(date)
        upperValue.setText("120")
        lowerValue.setText("80")
        saveButton.setOnClickListener {
            val data = BloodPressure(upperValue = upperValue.text.toString().toInt(), lowerValue = lowerValue.text.toString().toInt(), measureTime = date.toString(), note = note.text.toString())
            listener.dataIsReady(data)
            dismiss()
        }
        cancelButton.setOnClickListener {
            dismiss()
        }
        return view
    }
}