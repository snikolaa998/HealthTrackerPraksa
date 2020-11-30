package com.example.healthtrackerpraksa.ui.inputdialogs

import android.app.Dialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.widget.Button
import androidx.annotation.RequiresApi
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.Temperature
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.dialog_temperature.*
import java.time.LocalDateTime
import java.time.ZoneOffset


class TemperatureDialog(
    context: Context,
    private val listener: IDialogInputListener<Temperature>
) :
    Dialog(context, R.style.MyDialogTheme2) {


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_temperature)

        initSaveButton()
        initCancelButton()

        initDatePicker()



    }


    private fun initDatePicker() {
        tv_date_value_input.setOnClickListener {
            SpinnerDatePickerDialogBuilder()
                .context(this.context)
                .showTitle(true)
                .defaultDate(2020, 10, 27)
                .callback { view, year, monthOfYear, dayOfMonth ->
                    tv_date_value_input.text = "$dayOfMonth / ${monthOfYear + 1} / $year"
                }
                .build()
                .show()
        }
    }

    private fun initCancelButton() {
        val cancelButton = findViewById<Button>(R.id.btn_cancel_temp_input)
        cancelButton.setOnClickListener {
            dismiss()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initSaveButton() {
        val saveButton = findViewById<Button>(R.id.btn_save_temp_input)
        saveButton.setOnClickListener {

            listener.onDialogValueSubmitted(
                Temperature(
                    et_temperature.text.toString().toInt(), LocalDateTime.now().toEpochSecond(
                        ZoneOffset.ofTotalSeconds(7200)
                    ).toInt(), et_note_value_input.text.toString()
                )
            )
            dismiss()
        }
    }


}