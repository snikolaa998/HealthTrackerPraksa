package com.example.healthtrackerpraksa.ui.inputdialogs

import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TimePicker
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.models.Temperature
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.dialog_temperature.*
import java.util.*


class TemperatureDialog(
    context: Context,
    private val tempListener: (Temperature) -> Unit
) :
    Dialog(context, R.style.MyDialogTheme2), TimePickerDialog.OnTimeSetListener,
    RadioGroup.OnCheckedChangeListener {

    private val calendar: Calendar = Calendar.getInstance()
    private var unitOfMeasure: Char = 'C'


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_temperature)

        radioGroup.setOnCheckedChangeListener(this)

        initSaveButton()
        initCancelButton()
        initDatePicker()
        initTimePicker()
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

            tempListener(
                Temperature(
                    et_temp_value_input.text.toString(),
                    calendar.time,
                    et_note_value_input.text.toString(),
                    unitOfMeasure
                )
            )
            dismiss()
        }
    }

    private fun initTimePicker() {
        tv_time_value_input.setOnClickListener {
            val timePickerDialog = TimePickerDialog(
                context,
                AlertDialog.THEME_HOLO_LIGHT, this, 12, 0, true
            ).apply {
                window?.setBackgroundDrawableResource(android.R.color.transparent)
                setTitle("Pick Time")
            }
            timePickerDialog.show()

        }
    }

    private fun initDatePicker() {
        tv_date_value_input.setOnClickListener {
            SpinnerDatePickerDialogBuilder()
                .context(this.context)
                .showTitle(true)
                .defaultDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                .callback { _, year, monthOfYear, dayOfMonth ->
                    Log.i("CALENDAR", "initDatePicker: $dayOfMonth ")
                    calendar.set(year, monthOfYear, dayOfMonth)
                    tv_date_value_input.text = "$dayOfMonth / ${monthOfYear + 1} / $year"
                }
                .build()
                .show()
        }
    }

    override fun onTimeSet(timePicker: TimePicker?, hour: Int, minute: Int) {
        tv_time_value_input.text = "$hour  :  $minute"
        calendar.apply {
            set(Calendar.HOUR, hour)
            set(Calendar.MINUTE, minute)
        }
    }

    override fun onCheckedChanged(group: RadioGroup?, buttonId: Int) {
        when (buttonId) {
            R.id.rb_celsius -> unitOfMeasure = 'C'
            R.id.rb_fahrenheit -> unitOfMeasure = 'F'
        }
    }
}