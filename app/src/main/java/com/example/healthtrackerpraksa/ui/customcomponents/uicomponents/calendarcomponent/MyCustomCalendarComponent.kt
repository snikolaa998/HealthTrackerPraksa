package com.example.healthtrackerpraksa.ui.customcomponents.uicomponents.calendarcomponent

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.models.BloodPressure
import com.example.healthtrackerpraksa.models.BloodSugar
import com.example.healthtrackerpraksa.models.Temperature
import kotlinx.android.synthetic.main.my_custom_calendar.view.*
import java.util.*

class MyCustomCalendarComponent @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private val monthPage = MonthPage()
    val calendarAdapter = CalendarAdapter(monthPage)
    private val calendar = monthPage.getMonth()
    private var onChangeMonthButtonClicked: IOnChangeMonthButtonClickedListener? = null

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        inflater.inflate(R.layout.my_custom_calendar, this)
        rv_calendar_preview.layoutManager = GridLayoutManager(context, 7)
        rv_calendar_preview.adapter = calendarAdapter
        tv_date_label.text = monthPage.getMonthNameInfo()

        iv_arrow_previous.setOnClickListener {
            monthPage.previousMonth()
            tv_date_label.text = monthPage.getMonthNameInfo()
            onChangeMonthButtonClicked?.onChangeButtonClicked(calendar)
        }

        iv_arrow_next.setOnClickListener {
            monthPage.nextMonth()
            tv_date_label.text = monthPage.getMonthNameInfo()
            onChangeMonthButtonClicked?.onChangeButtonClicked(calendar)
        }
    }

    fun updateData(
        tempInputHistory: List<Temperature>,
        bloodSugarInputHistory: List<BloodSugar>,
        bloodPressureInputHistory: List<BloodPressure>
    ) {
        monthPage.updateData(tempInputHistory, bloodSugarInputHistory, bloodPressureInputHistory)
        calendarAdapter.notifyDataSetChanged()
    }

    fun setOnChangeMonthButtonClicked(onPreviousButtonListener: IOnChangeMonthButtonClickedListener) {
        onChangeMonthButtonClicked = onPreviousButtonListener
    }
}

interface IOnChangeMonthButtonClickedListener {
    fun onChangeButtonClicked(calendar: Calendar)
}

