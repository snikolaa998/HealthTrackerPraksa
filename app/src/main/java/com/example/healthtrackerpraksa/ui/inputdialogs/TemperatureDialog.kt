package com.example.healthtrackerpraksa.ui.inputdialogs

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.Temperature


class TemperatureDialog(
    context: Context,
    private val listener: IDialogInputListener<Temperature>
) :
    Dialog(context, R.style.MyDialogTheme2) {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_temperature)

        initSaveButton()
        initCancelButton()


    }

    private fun initCancelButton() {
        val cancelButton = findViewById<Button>(R.id.btn_cancel_temp_input)
        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    private fun initSaveButton() {
        val saveButton = findViewById<Button>(R.id.btn_save_temp_input)
        saveButton.setOnClickListener {
            val temperature = Temperature(37, 5, "Povisena temperatura!")
            listener.onDialogValueSubmitted(temperature)
            dismiss()
        }
    }


}