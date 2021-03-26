package com.example.healthapp

import android.R
import android.icu.util.Calendar
import android.icu.util.ULocale
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class CalendarActivity  : AppCompatActivity(){
    private var mDatabase: FirebaseDatabase? = null
    private var mDatabaseReference: DatabaseReference? = null
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null
    private var signInButton: Button? = null
    private var mAuth: FirebaseAuth? = null
    private var progressBar: ProgressBar? = null





    // Define the variable of CalendarView type
    // and TextView type;
    var calender: CalendarView? = null
    var date_view: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_calendar)
        val cal: Calendar = Calendar.getInstance(ULocale("en_US@calendar=japanese"))




        //yoinked firebase inits
        mDatabase = FirebaseDatabase.getInstance()
        mDatabaseReference = mDatabase!!.reference.child("Users")



//        mAuth = FirebaseAuth.getInstance()
//        userEmail = findViewById(R.id.email)
//        userPassword = findViewById(R.id.password)
//        signInButton = findViewById(R.id.sign_in)
//        progressBar = findViewById(R.id.progressBar)


    }

}