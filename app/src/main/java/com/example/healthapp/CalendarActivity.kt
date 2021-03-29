package com.example.healthapp

import android.icu.util.Calendar
import android.icu.util.ULocale
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity


class CalendarActivity  : AppCompatActivity(){


//https://www.youtube.com/watch?v=hHjFIG0TtA0
    // Define the variable of CalendarView type
    // and TextView type;
    var calendar: CalendarView? = null
    var dateView: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)




//        //yoinked firebase inits
//        mDatabase = FirebaseDatabase.getInstance()
//        mDatabaseReference = mDatabase!!.reference.child("Users")



//        mAuth = FirebaseAuth.getInstance()
//        userEmail = findViewById(R.id.email)
//        userPassword = findViewById(R.id.password)
//        signInButton = findViewById(R.id.sign_in)
//        progressBar = findViewById(R.id.progressBar)


    }

}