package com.example.healthtrackerpraksa.ui.inputdialogs

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.widget.Button
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.Temperature


class TemperaturePopupDialog(context: Context, private val listener: ITempDialog) :
    Dialog(context, R.style.MyDialogTheme2) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_temperature_popup)


        val saveButton = findViewById<Button>(R.id.btn_save_temp_input)
        saveButton.setOnClickListener {
            val temperature = Temperature(5, 5, 5, "")
            listener.tempDone(temperature)
            dismiss()

        }

        val cancelButton = findViewById<Button>(R.id.btn_cancel_temp_input)
        cancelButton.setOnClickListener {
            dismiss()
        }


    }


    interface ITempDialog {
        fun tempDone(temperature: Temperature)
    }
}