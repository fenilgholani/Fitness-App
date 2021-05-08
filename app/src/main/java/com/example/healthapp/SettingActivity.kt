package com.example.healthapp

import android.content.DialogInterface
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import de.hdodenhof.circleimageview.CircleImageView
import java.util.*

class SettingActivity: AppCompatActivity() {
    private var feet : EditText? = null
    private var inches : EditText? = null
    private var weight : EditText? = null
    private var weightUnit : TextView? = null
    private var lbs : Button? = null
    private var kg : Button? = null
    private var username: EditText? = null
    //    private var age : EditText? = null
    private var dob: DatePicker? = null
    private var submit : Button? = null
    private var avatar1 : CircleImageView? = null
    private var avatar2 : CircleImageView? = null
    private var avatar3 : CircleImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        username = findViewById(R.id.per_username)
        feet = findViewById(R.id.per_height_feet)
        inches = findViewById(R.id.per_height_inches)
        weight = findViewById(R.id.per_weight)
        weightUnit = findViewById(R.id.per_weight_unit)
        lbs = findViewById(R.id.per_lbs)
        kg = findViewById(R.id.per_kg)
//        age = findViewById(R.id.age)
        submit = findViewById(R.id.per_save)
        dob = findViewById(R.id.per_dob)
        avatar1 = findViewById(R.id.avatar1)
        avatar2 = findViewById(R.id.avatar2)
        avatar3 = findViewById(R.id.avatar3)

        lbs!!.setOnClickListener {
            weightUnit!!.text = "LBS"
        }

        kg!!.setOnClickListener {
            weightUnit!!.text = "KG"
        }

        avatar1!!.setOnClickListener {
            avatar1!!.circleBackgroundColor = getColor(R.color.main_green)
            Toast.makeText(
                applicationContext,
                "Selected Kettlebell avatar!",
                Toast.LENGTH_LONG
            ).show()
            UserInfo.setAvatar(avatar1!!)
        }

        avatar2!!.setOnClickListener {
            avatar2!!.circleBackgroundColor = getColor(R.color.main_green)
            Toast.makeText(
                applicationContext,
                "Selected Heart avatar!",
                Toast.LENGTH_LONG
            ).show()
            UserInfo.setAvatar(avatar2!!)
        }

        avatar3!!.setOnClickListener {
            avatar3!!.circleBackgroundColor = getColor(R.color.main_green)
            Toast.makeText(
                applicationContext,
                "Selected Weights avatar!",
                Toast.LENGTH_LONG
            ).show()
            UserInfo.setAvatar(avatar3!!)
        }

//        Think about the date
        val dateOfBirth = UserInfo.getDOB()
        var dayFormatter = SimpleDateFormat("MM.dd.yyyy")
        var daysof = dayFormatter.format(dateOfBirth).split(".")



        username!!.setText(UserInfo.getUsername())
        feet!!.setText(UserInfo.getHeightFt())
        inches!!.setText(UserInfo.getHeightIn())
        weight!!.setText(UserInfo.getWeight())
        weightUnit!!.text = UserInfo.getWeightUn()
//        if(dob != null)
            dob!!.updateDate(daysof[2].toInt(), daysof[0].toInt()-1, daysof[1].toInt())

        submit!!.setOnClickListener {
            //   Updating personal Info

            var user = username!!.text.toString()
            UserInfo.setUsername(user)

            var userHeightFt = feet!!.text.toString()
            UserInfo.setHeightFt(userHeightFt!!)
            var userHeightIn = inches!!.text.toString()
            UserInfo.setHeightIn(userHeightIn!!)

            var userWeight = weight!!.text.toString()
            var userWeightUn = weightUnit!!.text.toString()
            UserInfo.setWeight(userWeight!!)
            UserInfo.setWeightUn(userWeightUn!!)



            var userDOB = dob!!.getDate()
            UserInfo.setDOB(userDOB)

//            for ((k, v) in userHeight) {
//                Log.i("USER INFO", "Height:${k + v}")
//            }
//
//            for ((k, v) in userWeight) {
//                Log.i("USER INFO", "Weight:${k + v}")
//            }

            Log.i("USER INFO", "DOB: ${userDOB}")

            if (verifyFields()) {
                val intent = Intent(this@SettingActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }



        var logout:Button = findViewById(R.id.per_logout)
        logout.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Do you want to Log Out?")
                .setCancelable(false)
                .setPositiveButton("Proceed", DialogInterface.OnClickListener {
                    _,_->
                        var intent = Intent(this@SettingActivity, HomeActivity::class.java)
                        startActivity(intent)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                        dialog, _ -> dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Log Out")
            alert.show()

        }

    }

    private fun DatePicker.getDate(): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        return calendar.time
    }

    private fun verifyFields(): Boolean {
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