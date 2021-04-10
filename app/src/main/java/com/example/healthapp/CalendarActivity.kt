package com.example.healthapp

import android.app.Activity
import android.content.Intent
import java.util.Calendar
import android.icu.util.ULocale
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


class CalendarActivity  : AppCompatActivity(){


//https://www.youtube.com/watch?v=hHjFIG0TtA0
    // Define the variable of CalendarView type
    // and TextView type;
    var calendar: CalendarView? = null
    var dateView: TextView? = null
    var bottomNavigationView : BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        calendar = findViewById(R.id.calendarView)
        dateView = findViewById(R.id.textView)

//       Got Todays Exercise Data
        var exerciseData = Exercise.getExerciseData()
        // Date -> {Exercise Name->{Set#->[lbs,reps]}}
        var dateExercise= HashMap<String, HashMap<String, HashMap<Int, ArrayList<Int>>>>()

        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

//        var todayDate = ArrayList<EventDay>()
//        todayDate.add(c)
//        calendar!!.selectedDates = todayDate
//        c.add(Calendar.DAY_OF_MONTH, -5)
//        Log.d("DATE", "${c.get(Calendar.MONTH)} ${c.get(Calendar.DAY_OF_MONTH)} ${c.get(Calendar.YEAR)}")

        var todayDate = Calendar.getInstance()
        todayDate.set(2021, 3, 4)
        calendar!!.setDate(todayDate)

        dateExercise["${month+1}.$day.$year"] = exerciseData

        Log.i("Fenil", exerciseData.toString())

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView!!.selectedItemId = R.id.action_history
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if(requestCode==1 && resultCode == Activity.RESULT_OK ){
//            var exerciseData = data!!.getSerializableExtra("exerciseData")
//            Log.i("Fenil", exerciseData.toString())
//        }
//    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.action_history -> {
                val intent = Intent(this, CalendarActivity::class.java)
                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.action_workout -> {
                val intent = Intent(this, BetterEx::class.java)
                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
//            R.id.action_information -> {
//                val intent = Intent(this, InfoActivity::class.java)
//                startActivity(intent)
////                return@OnNavigationItemSelectedListener true
//            }
        }
        false
    }

}