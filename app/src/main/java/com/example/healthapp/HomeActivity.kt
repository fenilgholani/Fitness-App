package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private var signInButton: Button? = null
    private var signUpButton: Button? = null
    private var calendarButton: Button? = null
    private var dummySearch: Button? = null
    private var dummyTodayEx: Button? = null
    private var timer: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        signInButton = findViewById(R.id.sign_in)
        signUpButton = findViewById(R.id.sign_up)
        calendarButton = findViewById(R.id.sign_up)
        dummySearch = findViewById(R.id.dummy_button)
        dummyTodayEx = findViewById(R.id.dummyTodayExercise)
        timer = findViewById(R.id.timer)


        signInButton!!.setOnClickListener {
            signIn()
        }

        signUpButton!!.setOnClickListener {
            signUp()
        }

        calendarButton!!.setOnClickListener {
            signUp()
        }

        dummySearch!!.setOnClickListener {
            search()
        }

        dummyTodayEx!!.setOnClickListener{
            todayExercise()
        }

        timer!!.setOnClickListener{
            timer()
        }


    }

    private fun signIn() {
        val intent = Intent(this@HomeActivity, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun signUp() {
        val intent = Intent(this@HomeActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

    private fun search(){
        val intent = Intent(this@HomeActivity, InfoActivity::class.java)
        startActivity(intent)
    }

    private fun todayExercise(){
        val intent = Intent(this@HomeActivity, TodayExerciseActivity::class.java)
        startActivity(intent)
    }

    private fun calendar() {
        val intent = Intent(this@HomeActivity, CalendarActivity::class.java)
        startActivity(intent)
    }

    private fun timer() {
        val intent = Intent(this@HomeActivity, TimerActivity2::class.java)
        startActivity(intent)
    }
}