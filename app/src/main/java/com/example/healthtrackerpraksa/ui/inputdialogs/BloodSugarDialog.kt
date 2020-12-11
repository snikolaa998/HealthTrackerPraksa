package com.example.healthtrackerpraksa.ui.inputdialogs

import android.app.AlertDialog
import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.models.BloodSugar
import com.tsongkha.spinnerdatepicker.SpinnerDatePickerDialogBuilder
import kotlinx.android.synthetic.main.dialog_blood_sugar.*
import java.util.*


class BloodSugarDialog(
    context: Context,
    private val bloodSugarListener: (BloodSugar) -> Unit
) : Dialog(context, R.style.MyDialogTheme2),
    TimePickerDialog.OnTimeSetListener {

    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_blood_sugar)

        initDatePicker()
        initTimePicker()
        initSaveButton()
        initCancelButton()
    }

    private fun initCancelButton() {
        btn_blood_sugar_cancel_temp_input.setOnClickListener {
            dismiss()
        }
    }

    private fun initSaveButton() {
        btn_blood_sugar_save_temp_input.setOnClickListener {
            bloodSugarListener(
                BloodSugar(
                    et_blood_sugar_value_input.text.toString(),
                    calendar.time,
                    et_blood_sugar_note_value_input.text.toString()
                )
            )
            dismiss()
        }
    }

    private fun initTimePicker() {
        tv_blood_sugar_time_value_input.setOnClickListener {
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
        tv_blood_sugar_date_value_input.setOnClickListener {
            SpinnerDatePickerDialogBuilder()
                .context(context)
                .defaultDate(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
                .showTitle(true)
                .callback { _, year, monthOfYear, dayOfMonth ->
                    Log.i("CALENDAR", "initDatePicker: $dayOfMonth ")
                    calendar.set(year, monthOfYear, dayOfMonth)
                    tv_blood_sugar_date_value_input.text =
                        "$dayOfMonth / ${monthOfYear + 1} / $year"
                }
                .build()
                .show()
        }
    }

    override fun onTimeSet(view: TimePicker?, hour: Int, minute: Int) {
        tv_blood_sugar_time_value_input.text = "$hour  :  $minute"
        calendar.apply {
            set(Calendar.HOUR, hour)
            set(Calendar.MINUTE, minute)
        }
    }
}