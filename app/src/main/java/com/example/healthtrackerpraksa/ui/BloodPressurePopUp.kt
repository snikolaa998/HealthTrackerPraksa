package com.example.healthtrackerpraksa.ui

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.healthtrackerpraksa.R

class BloodPressurePopUp : DialogFragment() {
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
        saveButton.setOnClickListener {
            Toast.makeText(activity, "Akcija za save button", Toast.LENGTH_SHORT).show()
        }
        cancelButton.setOnClickListener {
            Toast.makeText(activity, "Akcija za cancel button", Toast.LENGTH_SHORT).show()
        }
        return view
    }
}