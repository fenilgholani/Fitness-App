package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.HashMap

class RecordActivity: AppCompatActivity() {
    private var feet : EditText? = null
    private var inches : EditText? = null
    private var weight : EditText? = null
    private var weightUnit : TextView? = null
    private var lbs : Button? = null
    private var kg : Button? = null
//    private var age : EditText? = null
    private var dob: DatePicker? = null
    private var submit : Button? = null
    private var userHeightFt: String ?= null
    private var userHeightIn: String ?= null
    private var userWeightUn : String ?= null
    private var userWeight : String ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_record)

        feet = findViewById(R.id.height_feet)
        inches = findViewById(R.id.height_inches)
        weight = findViewById(R.id.weight)
        weightUnit = findViewById(R.id.weight_unit)
        lbs = findViewById(R.id.lbs)
        kg = findViewById(R.id.kg)
//        age = findViewById(R.id.age)
        submit = findViewById(R.id.submit)
        dob = findViewById(R.id.dob)

        lbs!!.setOnClickListener {
            weightUnit!!.text = "LBS"
        }

        kg!!.setOnClickListener {
            weightUnit!!.text = "KG"
        }

        val c = Calendar.getInstance()
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        dob!!.updateDate(2005,month,day)

        submit!!.setOnClickListener {
            //   Updating personal Info

            userHeightFt = feet!!.text.toString()
            UserInfo.setHeightFt(userHeightFt!!)
            userHeightIn = inches!!.text.toString()
            UserInfo.setHeightIn(userHeightIn!!)

            userWeight = weight!!.text.toString()
            userWeightUn = weightUnit!!.text.toString()
            UserInfo.setWeight(userWeight!!)
            UserInfo.setWeightUn(userWeightUn!!)

            var userDOB = dob!!.getDate()
            UserInfo.setDOB(userDOB)

//            for((k,v) in userHeight){
//                Log.i("USER INFO", "Height:${k + v}")
//            }
//
//            for((k,v) in userWeight){
//                Log.i("USER INFO", "Weight:${k + v}")
//            }

            Log.i("USER INFO", "DOB: ${userDOB}")

            if(verifyFields()) {
                val intent = Intent(this@RecordActivity, SignInActivity::class.java)
                startActivity(intent)
            }
        }

    }

    private fun DatePicker.getDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.time
    }

    private fun verifyFields(): Boolean{
        var verified = true
        when {
            TextUtils.isEmpty(feet!!.text.toString()) -> {
                feet!!.error = "Please enter Height in Ft"
                verified = false
            }
            TextUtils.isEmpty(inches!!.text.toString()) -> {
                inches!!.error = "Please enter Height in Inches"
                verified = false
            }
            TextUtils.isEmpty(weight!!.text.toString()) -> {
                weight!!.error = "Please enter Weight"
                verified = false
            }
            weightUnit!!.text.isEmpty() -> {
                Toast.makeText(
                    applicationContext,
                    "Please select Weight Unit",
                    Toast.LENGTH_LONG
                ).show()
                verified = false
            }
            (Integer.parseInt(feet!!.text.toString()) > 8) -> {
                Toast.makeText(
                    applicationContext,
                    "Please enter a value for Feet less than or equal to 8",
                    Toast.LENGTH_LONG
                ).show()
                verified = false
            }
            (Integer.parseInt(inches!!.text.toString()) > 11) -> {
                Toast.makeText(
                    applicationContext,
                    "Please enter a value for Inches less than or equal to 11",
                    Toast.LENGTH_LONG
                ).show()
                verified = false
            }
        }

        return verified
    }
}