package com.example.healthapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.os.Parcelable
import android.text.TextUtils
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import de.hdodenhof.circleimageview.CircleImageView
import java.io.ByteArrayOutputStream
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
    private var bottomNavigationView: BottomNavigationView? = null

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
            avatar2!!.circleBackgroundColor = getColor(R.color.white)
            avatar3!!.circleBackgroundColor = getColor(R.color.white)
            Toast.makeText(
                applicationContext,
                "Selected Kettlebell avatar!",
                Toast.LENGTH_LONG
            ).show()
            UserInfo.setAvatar(avatar1!!)
        }

        avatar2!!.setOnClickListener {
            avatar2!!.circleBackgroundColor = getColor(R.color.main_green)
            avatar1!!.circleBackgroundColor = getColor(R.color.white)
            avatar3!!.circleBackgroundColor = getColor(R.color.white)
            Toast.makeText(
                applicationContext,
                "Selected Heart avatar!",
                Toast.LENGTH_LONG
            ).show()
            UserInfo.setAvatar(avatar2!!)
        }

        avatar3!!.setOnClickListener {
            avatar3!!.circleBackgroundColor = getColor(R.color.main_green)
            avatar1!!.circleBackgroundColor = getColor(R.color.white)
            avatar2!!.circleBackgroundColor = getColor(R.color.white)
            Toast.makeText(
                applicationContext,
                "Selected Weights avatar!",
                Toast.LENGTH_LONG
            ).show()
            UserInfo.setAvatar(avatar3!!)
        }

//        Think about the date
        var dateOfBirth: Date? =  null
        var dayFormatter: SimpleDateFormat?= null
        var daysof:List<String>? = null


        if(!UserInfo.getUsername().equals("Guest")) {
            dateOfBirth = UserInfo.getDOB()
            dayFormatter = SimpleDateFormat("MM.dd.yyyy")
            daysof = dayFormatter.format(dateOfBirth).split(".")

            username!!.setText(UserInfo.getUsername())
            feet!!.setText(UserInfo.getHeightFt())
            inches!!.setText(UserInfo.getHeightIn())
            weight!!.setText(UserInfo.getWeight())
            weightUnit!!.text = UserInfo.getWeightUn()
            dob!!.updateDate(daysof[2].toInt(), daysof[0].toInt() - 1, daysof[1].toInt())
        }
        if(UserInfo.getUsername().equals("Guest"))
            username!!.setHint("Username")
        
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
                var bitmap: Bitmap? = BitmapFactory.decodeResource(resources, UserInfo.getAvatar()!!.id)
                Log.i("BITMAP", bitmap.toString())
                val bs = ByteArrayOutputStream()
                bitmap!!.compress(Bitmap.CompressFormat.PNG, 50, bs)
                intent.putExtra("byteArray", bs.toByteArray())
                startActivity(intent)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Ensure all fields are entered correctly.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }



        var logout:Button = findViewById(R.id.per_logout)
        logout.setOnClickListener{
            val dialogBuilder = AlertDialog.Builder(this)

            dialogBuilder.setMessage("Do you want to Log Out?")
                .setCancelable(false)
                .setPositiveButton("Proceed", DialogInterface.OnClickListener { _, _ ->
                    var intent = Intent(this@SettingActivity, HomeActivity::class.java)
                    startActivity(intent)
                })
                .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, _ ->
                    dialog.cancel()
                })
            val alert = dialogBuilder.create()
            alert.setTitle("Log Out")
            alert.show()

        }

        bottomNavigationView = findViewById(R.id.bottom_navigation_setting)
        bottomNavigationView!!.selectedItemId = R.id.action_profile
//        bottomNavigationView!!.menu.findItem(R.id.action_profile).isEnabled = false
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_profile -> {
                    val intent = Intent(this@SettingActivity, MainActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_calendar -> {
                    val intent = Intent(this@SettingActivity, CalendarActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_workout -> {
                    val intent = Intent(this@SettingActivity, BetterEx::class.java)
                    startActivity(intent)
                }
            }
            false
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