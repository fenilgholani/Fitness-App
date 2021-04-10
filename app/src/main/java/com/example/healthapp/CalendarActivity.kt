package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.github.sundeepk.compactcalendarview.CompactCalendarView
import com.github.sundeepk.compactcalendarview.CompactCalendarView.CompactCalendarViewListener
import com.github.sundeepk.compactcalendarview.domain.Event
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.HashMap


class CalendarActivity  : AppCompatActivity(){


//https://www.youtube.com/watch?v=hHjFIG0TtA0
    // Define the variable of CalendarView type
    // and TextView type;
    var calendar: CompactCalendarView? = null
    var dateView: TextView? = null
    var bottomNavigationView : BottomNavigationView? = null
    var monthName: TextView? = null
    var backArrow: ImageView? = null
    var nextArrow: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendar = findViewById(R.id.calendarView)
        dateView = findViewById(R.id.textView)
        monthName = findViewById(R.id.month_name)
        backArrow = findViewById(R.id.back_arrow)
        nextArrow = findViewById(R.id.next_arrow)

        // Got Today's Exercise Data
        var exerciseData = Exercise.getExerciseData()
        // Date -> {Exercise Name->{Set#->[lbs,reps]}}
        var dateExercise= HashMap<String, HashMap<String, HashMap<Int, ArrayList<Int>>>>()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        dateExercise["${month + 1}.$day.$year"] = exerciseData
        calendar!!.setFirstDayOfWeek(Calendar.SUNDAY)
        var event = Event(R.color.main_blue, 1617499276000L, "New event on this day!")
        calendar!!.addEvent(event)

        calendar!!.setListener(object : CompactCalendarViewListener {
            override fun onDayClick(dateClicked: Date) {
                val events: List<Event> = calendar!!.getEvents(dateClicked)
                Log.d("EVENT", "Day was clicked: $dateClicked with events $events")
            }

            override fun onMonthScroll(firstDayOfNewMonth: Date) {
                Log.d("MONTH", "Month was scrolled to: $firstDayOfNewMonth")
                when(firstDayOfNewMonth.month){
                    0 ->{
                        monthName!!.text = "January"
                    }
                    1 ->{
                        monthName!!.text = "February"
                    }
                    2 ->{
                        monthName!!.text = "March"
                    }
                    3 ->{
                        monthName!!.text = "April"
                    }
                    4 ->{
                        monthName!!.text = "May"
                    }
                    5 ->{
                        monthName!!.text = "June"
                    }
                    6 ->{
                        monthName!!.text = "July"
                    }
                    7 ->{
                        monthName!!.text = "August"
                    }
                    8 ->{
                        monthName!!.text = "September"
                    }
                    9 ->{
                        monthName!!.text = "October"
                    }
                    10 ->{
                        monthName!!.text = "November"
                    }
                    11 ->{
                        monthName!!.text = "December"
                    }
                }

            }
        })

        backArrow!!.setOnClickListener { calendar!!.scrollLeft() }
        nextArrow!!.setOnClickListener { calendar!!.scrollRight() }

        Log.i("Fenil", exerciseData.toString())

        bottomNavigationView = findViewById(R.id.bottom_navigation_calendar)
        bottomNavigationView!!.selectedItemId = R.id.action_calendar
        bottomNavigationView!!.menu.findItem(R.id.action_calendar).isEnabled = false
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.action_workout -> {
                val intent = Intent(this, BetterEx::class.java)
                startActivity(intent)
            }
        }
        false
    }

}