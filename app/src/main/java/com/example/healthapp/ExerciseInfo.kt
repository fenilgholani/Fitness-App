package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ExerciseInfo : AppCompatActivity() {

    private var bigTextView: TextView? = null
    private var infotextView: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise_info)

        var intent = getIntent()
        var exerciseName = intent.getStringExtra("exerciseName")

        bigTextView = findViewById(R.id.textView)
        infotextView = findViewById(R.id.textView2)

        bigTextView!!.text = exerciseName

    }



}