package com.example.healthapp

import android.app.Activity
import android.content.Intent
import android.icu.util.Calendar
import android.icu.util.ULocale
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView


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


//        var intent = intent
        var exerciseData = Exercise.getExerciseData()
//
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