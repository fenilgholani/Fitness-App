package com.example.healthapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*

class TodayExerciseActivity : AppCompatActivity() {

    private var save: Button? = null
    private var add: Button? = null
    var bottomNavigationView : BottomNavigationView? = null
    private var today_recyclerView: RecyclerView?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_exercise)

        var intent = intent
        var exerciseNames = intent.getStringArrayListExtra("exerciseName")
        var dummy_finish = findViewById<Button>(R.id.dummy_finish)

        var in1 = Intent(this, CalendarActivity::class.java)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        today_recyclerView = findViewById(R.id.today_recyclerView)
        today_recyclerView!!.layoutManager = LinearLayoutManager(this)
        var adapter = exerciseNames?.let { ExerciseAdapter(it) }
        today_recyclerView!!.adapter = adapter

        dummy_finish.setOnClickListener{
            adapter!!.notifyDataSetChanged()

            for(b in adapter.getCompleteButton()!!.iterator()){
                b.performClick()
            }
            Exercise.setExerciseData(adapter.getexerciseData(),"${month+1}.$day.$year")

//            in1.putExtra("exerciseData", adapter.getexerciseData())
            //startActivity(in1)
        }


//        add = findViewById(R.id.add)
//        save = findViewById(R.id.save)
//
//
//        add!!.setOnClickListener{
//            Toast.makeText(applicationContext, "Exercise Added", Toast.LENGTH_SHORT).show()
//        }
//
//        save!!.setOnClickListener{
//            Toast.makeText(applicationContext, "Exercise Saved", Toast.LENGTH_SHORT).show()
//        }

        bottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView!!.selectedItemId = R.id.action_workout
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu
        menuInflater.inflate(R.menu.menu_timer, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         when (item.itemId) {
            R.id.action_timer -> {
                val intent = Intent(this, TimerActivity::class.java)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

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