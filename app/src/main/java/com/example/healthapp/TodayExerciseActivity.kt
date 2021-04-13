package com.example.healthapp

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.HashMap

class TodayExerciseActivity : AppCompatActivity() {

    private var save: Button? = null
    private var add: Button? = null
    var bottomNavigationView : BottomNavigationView? = null
    private var todayRecyclerView: RecyclerView?= null
    private var finished = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_exercise)

        var intent = intent
        var exerciseNames = intent.getStringArrayListExtra("exerciseName")
        var finishWorkout = findViewById<Button>(R.id.dummy_finish)

        // var in1 = Intent(this, CalendarActivity::class.java)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        todayRecyclerView = findViewById(R.id.today_recyclerView)
        todayRecyclerView!!.layoutManager = LinearLayoutManager(this)
        var adapter = exerciseNames?.let { ExerciseAdapter(it) }
        todayRecyclerView!!.adapter = adapter

        finishWorkout.setOnClickListener{
            finished = true
            adapter!!.notifyDataSetChanged()

            for(b in adapter.getCompleteButton()!!.iterator()){
                b.performClick()
            }
            Exercise.setExerciseData(adapter.getexerciseData(),"${month+1}.$day.$year")


            // Setting the exercise for the day
            var dateExercise = HashMap<String, HashMap<String, HashMap<Int, ArrayList<Int>>>>()

            if(month > 9)
                dateExercise["${month+1}.$day.$year"] = Exercise.getExerciseData()
            else
                dateExercise["0${month+1}.$day.$year"] = Exercise.getExerciseData()

            DateExercise.setExerciseData( dateExercise)

            Log.i("Date Exercise", DateExercise.getExerciseData().toString())

            // in1.putExtra("exerciseData", adapter.getexerciseData())
            // startActivity(in1)
            var intent = Intent(this@TodayExerciseActivity, CalendarActivity::class.java)
            startActivity(intent)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation_today_exercise)
        bottomNavigationView!!.selectedItemId = R.id.action_workout
        bottomNavigationView!!.menu.findItem(R.id.action_workout).isEnabled = false
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
            }
            R.id.action_calendar -> {
                val intent = Intent(this, CalendarActivity::class.java)
                startActivity(intent)
            }
        }
        false
    }

    override fun onBackPressed() {

        if(!finished){
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Do you want to leave Today's Workout? Data will not be saved.")
                .setCancelable(false)
                .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                        _,_->
                    super.onBackPressed()
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Leaving So Soon?")
            alert.show()
        }

    }
}