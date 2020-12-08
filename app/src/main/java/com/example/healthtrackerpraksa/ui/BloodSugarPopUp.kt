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
import com.example.healthtrackerpraksa.interfaces.BloodSugarDataIsReady
import com.example.healthtrackerpraksa.persistence.model.BloodSugar
import java.text.SimpleDateFormat
import java.util.*

class BloodSugarPopUp(private val listener: BloodSugarDataIsReady) : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return super.onCreateDialog(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.dialog_add_blood_sugar, container, false)

        val saveButton = view.findViewById<Button>(R.id.btn_save_blood_sugar)
        val cancelButton = view.findViewById<Button>(R.id.btn_cancel_blood_sugar)
        val etTime = view.findViewById<EditText>(R.id.et_time_blood_sugar)
        val etDate = view.findViewById<EditText>(R.id.et_date_blood_sugar)
        val currentValue = view.findViewById<EditText>(R.id.et_current_blood_sugar)
        val note = view.findViewById<EditText>(R.id.et_note_blood_sugar)

        val calendar = Calendar.getInstance()
        val dateTime = calendar.time

        val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(dateTime)
        val date = SimpleDateFormat("dd/MM", Locale.getDefault()).format(dateTime)

        etTime.setText(time)
        etDate.setText(date)

        saveButton.setOnClickListener {
            val data = BloodSugar(value = currentValue.text.toString().toInt(), measureTime = dateTime, note = note.text.toString())
            listener.bloodSugarDataIsReady(data)
            dismiss()
        }
        cancelButton.setOnClickListener {
            dismiss()
        }
        return view
    }
}