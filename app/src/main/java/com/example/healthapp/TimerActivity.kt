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

    var countdown: CountDownTimer ?= null
    var timer: TextView ?= null
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

    fun timerStarting(){

        findViewById<LinearLayout>(R.id.timer_layout).isVisible = true
        findViewById<LinearLayout>(R.id.stopwatch_layout).isVisible = false

        timer = findViewById(R.id.textView);

        var start: ImageButton = findViewById(R.id.timer_start)

        start.setOnClickListener {

            var hour:EditText = findViewById(R.id.hour)
            var min:EditText = findViewById(R.id.mins)
            var second:EditText = findViewById(R.id.second)


            var hour_cal: Int = 0
            var min_cal: Int = 0
            var sec_cal: Int = 0

            if (!hour.text.toString().equals("")){
                hour_cal = hour.text.toString().toInt()
            }
            if ( !min.text.toString().equals("")){
                min_cal = min.text.toString().toInt()
            }
            if (!second.text.toString().equals("")){
                sec_cal = second.text.toString().toInt()
            }


//                Log.i("Fenil",(hour_cal * 3600 + min_cal * 60 + sec_cal).toLong().toString())

            countdown = CountDownTimer(
                ((hour_cal * 3600 + min_cal * 60 + sec_cal) * 1000).toLong(),
                1000
            ) {

                override fun onTick(millisUntilFinished: Long) {

                    findViewById<ImageButton>(R.id.timer_stopped).setOnClickListener{

                    }
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

                }

                override fun onFinish() {
                    Log.i("Hello", "Finished")
                }


            }

            countdown.start()

        }
        }




    private fun timerResume() {
        Log.i("min", java.lang.Long.toString(min.toLong()))
        Log.i("Sec", java.lang.Long.toString(sec.toLong()))
        timerStart(milliLeft)
    }
    }

