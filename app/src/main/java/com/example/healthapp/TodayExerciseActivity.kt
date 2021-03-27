package com.example.healthapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class TodayExerciseActivity : AppCompatActivity() {

    private var save: Button? = null
    private var add: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today_exercise)

        add = findViewById(R.id.add)
        save = findViewById(R.id.save)


        add!!.setOnClickListener{
            Toast.makeText(applicationContext, "Exercise Added", Toast.LENGTH_SHORT).show()
        }

        save!!.setOnClickListener{
            Toast.makeText(applicationContext, "Exercise Saved", Toast.LENGTH_SHORT).show()
        }
    }
}