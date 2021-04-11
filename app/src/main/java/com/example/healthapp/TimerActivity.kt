package com.example.healthapp

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible

class TimerActivity : AppCompatActivity() {


    private lateinit var countDown: CountDownTimer
    var timer: TextView? = null
    var chronometer: Chronometer? = null
    var btStart: ImageButton? = null
    var btStop: ImageButton? = null
    private var isResume = false
    var handler: Handler? = null
    var tMillisec: Long = 0
    var tStart: Long = 0
    var tBuff: Long = 0
    var tUpdate = 0L
    var sec = 0
    var min = 0
    var milliSec = 0
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer)


        timerStarting()

        findViewById<Button>(R.id.stopwatch).setOnClickListener {

            findViewById<LinearLayout>(R.id.stopwatch_layout).isVisible = true
            findViewById<LinearLayout>(R.id.timer_layout).isVisible = false

            chronometer = findViewById(R.id.chronometer)
            btStart = findViewById(R.id.bt_start)
            btStop = findViewById(R.id.bt_stop)
            handler = Handler()

            btStart!!.setOnClickListener {
                if (!isResume) {
                    tStart = SystemClock.uptimeMillis()
                    handler!!.postDelayed(runnable, 0)
                    chronometer!!.start()
                    isResume = true
                    btStop!!.visibility = View.GONE
                    btStart!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext, // Context
                            R.drawable.ic_pause // Drawable
                        )
                    )
                } else {
                    tBuff += tMillisec
                    handler!!.removeCallbacks(runnable)
                    chronometer!!.stop()
                    isResume = false
                    btStop!!.visibility = View.VISIBLE
                    btStart!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext, // Context
                            R.drawable.ic_play // Drawable
                        )
                    )
                }
            }

            btStop!!.setOnClickListener {
                if (!isResume) {
                    btStart!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext, // Context
                            R.drawable.ic_play // Drawable
                        )
                    )
                    tMillisec = 0L
                    tStart = 0L
                    tBuff = 0L
                    tUpdate = 0L
                    sec = 0
                    min = 0
                    milliSec = 0
                    chronometer!!.text = "00:00:00"
                }
            }
        }

        findViewById<Button>(R.id.timer).setOnClickListener {

            timerStarting()


        }


    }

    val runnable: Runnable = object : Runnable {
        override fun run() {
            tMillisec = SystemClock.uptimeMillis() - tStart
            tUpdate = tBuff + tMillisec
            sec = (tUpdate / 1000).toInt()
            min = sec / 60
            sec %= 60
            milliSec = (tUpdate % 100).toInt()
            chronometer!!.text =
                String.format("%02d", min) + ":" + String.format(
                    "%02d",
                    sec
                ) + ":" + String.format("%02d", milliSec)
            handler!!.postDelayed(this, 60)
        }
    }

    fun timerStarting() {

        findViewById<LinearLayout>(R.id.timer_layout).isVisible = true
        findViewById<LinearLayout>(R.id.stopwatch_layout).isVisible = false


        var start: ImageButton = findViewById(R.id.timer_start)

        findViewById<ImageButton>(R.id.timer_stopped).setOnClickListener{

            var hour: EditText = findViewById(R.id.hour)
            var min: EditText = findViewById(R.id.mins)
            var second: EditText = findViewById(R.id.second)

            hour.setText("")
            min.setText("")
            second.setText("")

            hour.hint = "00"
            min.hint = "00"
            second.hint = "00"
        }

        start.setOnClickListener {

            //clicked
            count++

            var hour: EditText = findViewById(R.id.hour)
            var min: EditText = findViewById(R.id.mins)
            var second: EditText = findViewById(R.id.second)


            var hour_cal: Int = 0
            var min_cal: Int = 0
            var sec_cal: Int = 0

            if (!hour.text.toString().equals("")) {
                hour_cal = hour.text.toString().toInt()
            }
            if (!min.text.toString().equals("")) {
                min_cal = min.text.toString().toInt()
            }
            if (!second.text.toString().equals("")) {
                sec_cal = second.text.toString().toInt()
            }


            //                Log.i("Fenil",.toLong().toString())

            var current_time = (hour_cal * 3600 + min_cal * 60 + sec_cal)



            Log.i("Count:", "Count: $count isResume: $isResume")
            // The timer is paused
            if (count % 2 != 0) {
                isResume = false
                //play button is created
                timer_work(
                    (current_time * 1000).toLong(),
                    hour,
                    min,
                    second
                )

                findViewById<ImageButton>(R.id.timer_stopped)!!.visibility = View.GONE
                findViewById<ImageButton>(R.id.timer_start)!!.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext, // Context
                        R.drawable.ic_pause // Drawable
                    )
                )
            }


        }



    }

    fun timer_work(time: Long, hour: EditText, min: EditText, second: EditText) {

        countDown = object : CountDownTimer(time, 1000) {

            override fun onTick(millisUntilFinished: Long) {


                hour.setText(
                    String.format(
                        "%02d",
                        ((millisUntilFinished / (1000 * 60 * 60)) % 24).toInt()
                    )
                )
                min.setText(
                    String.format(
                        "%02d",
                        ((millisUntilFinished / (1000 * 60)) % 60).toInt()
                    )
                )
                second.setText(
                    String.format(
                        "%02d",
                        ((millisUntilFinished / 1000) % 60).toInt()
                    )
                )

                Log.i("Fenil", millisUntilFinished.toString())


                // the timer is paused
                if (count % 2 == 0) {
                    isResume = true
                    countDown.cancel()

                    findViewById<ImageButton>(R.id.timer_stopped)!!.visibility = View.VISIBLE
                    findViewById<ImageButton>(R.id.timer_start)!!.setImageDrawable(
                        ContextCompat.getDrawable(
                            applicationContext, // Context
                            R.drawable.ic_play // Drawable
                        )
                    )
                }

                // clicks on pause button


            }

            override fun onFinish() {
                Log.i("Hello", "Finished")
                hour.setText("")
                min.setText("")
                second.setText("")

                hour.hint = "00"
                min.hint = "00"
                second.hint = "00"
                isResume = false
                findViewById<android.widget.ImageButton>(com.example.healthapp.R.id.timer_stopped)!!.visibility =
                    android.view.View.VISIBLE
                findViewById<android.widget.ImageButton>(com.example.healthapp.R.id.timer_start)!!.setImageDrawable(
                    androidx.core.content.ContextCompat.getDrawable(
                        applicationContext, // Context
                        com.example.healthapp.R.drawable.ic_play // Drawable
                    )
                )

            }




        }
        countDown.start()
    }
}








