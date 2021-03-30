package com.example.healthapp

import android.os.Bundle
import android.os.Handler
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class TimerActivity : AppCompatActivity() {
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
}