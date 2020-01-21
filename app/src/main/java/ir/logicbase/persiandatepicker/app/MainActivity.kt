package ir.logicbase.persiandatepicker.app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import ir.logicbase.persiandatepicker.CalendarConstraints
import ir.logicbase.persiandatepicker.MaterialDatePicker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val calendar = MaterialDatePicker.todayCalendar()
        val colorState1 = R.color.colorCalendarState1
        val colorState2 = R.color.colorCalendarState2
        val colorState3 = R.color.colorCalendarState3
        val dayToColorMap = mutableMapOf<Long, Int>()
        repeat(10) {
            dayToColorMap[calendar.timeInMillis] = colorState1
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        repeat(10) {
            dayToColorMap[calendar.timeInMillis] = colorState2
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        repeat(10) {
            dayToColorMap[calendar.timeInMillis] = colorState3
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }
        val hintTitleToColor =
            mapOf("حالت اول" to colorState1, "حالت دوم" to colorState2, "حالت سوم" to colorState3)

        fab.setOnClickListener {
            val builder = MaterialDatePicker.Builder.datePicker()
            val today = MaterialDatePicker.todayCalendar()
            val start = MaterialDatePicker.otherMonthCalendar(2, false)
            val end = MaterialDatePicker.otherMonthCalendar(6, true)
            builder.setSelection(today.timeInMillis)

            val constraintsBuilder = CalendarConstraints.Builder()
            constraintsBuilder.setStart(start.timeInMillis) // start month
            constraintsBuilder.setEnd(end.timeInMillis) // end month
            constraintsBuilder.setOpenAt(today.timeInMillis) // calendar first visible month when opening
            constraintsBuilder.setDayToItemBackgroundColor(dayToColorMap)
            constraintsBuilder.setHintTitleToColor(hintTitleToColor)

            builder.setCalendarConstraints(constraintsBuilder.build())
            builder.setTheme(R.style.App_Theme_PersianDatePicker)
            val picker = builder.build()
            picker.addOnPositiveButtonClickListener { selection ->
                Toast.makeText(
                    applicationContext,
                    "selection : $selection = ${picker.headerText}",
                    Toast.LENGTH_LONG
                ).show()
            }
            picker.show(supportFragmentManager, picker.toString())
        }
    }

}
