package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class RecordActivity: AppCompatActivity() {
    private var feet : EditText? = null
    private var inches : EditText? = null
    private var weight : EditText? = null
    private var weightUnit : TextView? = null
    private var lbs : Button? = null
    private var kg : Button? = null
    private var age : EditText? = null
    private var submit : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        feet = findViewById(R.id.height_feet)
        inches = findViewById(R.id.height_inches)
        weight = findViewById(R.id.weight)
        weightUnit = findViewById(R.id.weight_unit)
        lbs = findViewById(R.id.lbs)
        kg = findViewById(R.id.kg)
        age = findViewById(R.id.age)
        submit = findViewById(R.id.submit)

        lbs!!.setOnClickListener {
            weightUnit!!.text = "LBS"
        }

        kg!!.setOnClickListener {
            weightUnit!!.text = "KG"
        }

        submit!!.setOnClickListener {
            val intent = Intent(this@RecordActivity, SignInActivity::class.java)
            startActivity(intent)
        }

    }
}