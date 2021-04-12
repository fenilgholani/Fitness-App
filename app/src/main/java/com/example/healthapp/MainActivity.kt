package com.example.healthapp

import android.content.Intent
import android.graphics.Color
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var bottomNavigationView: BottomNavigationView? = null
    private var userName: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        setSupportActionBar(findViewById(R.id.toolbar))
//         TODO: could use floating action for potential share feature?
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }

//        // Settings
//        val settings: Button = findViewById(R.id.action_setting)
//
//        settings.setOnClickListener{
//            SettingActivity()
//        }

        var lineChart: LineChart = findViewById(R.id.lineChart)


        //Part1
        val entries = ArrayList<Entry>()
        val xLabel: ArrayList<String> = ArrayList()


        //Part2
        entries.add(Entry(1618252200000f, 100f))
        entries.add(Entry(1618338600000f, 200f))
        entries.add(Entry(1618425000000f, 700f))
        entries.add(Entry(1618511400000f, 200f))
        entries.add(Entry(1618597800000f, 160f))
        entries.add(Entry(1618684200000f, 520f))
        entries.add(Entry(1618770600000f, 360f))

        // Label in the Xaxis
//        xLabel.add("7")
//        xLabel.add("14")
//        xLabel.add("21")
//        xLabel.add("28")
//        xLabel.add("35")

        val xAxis: XAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM


//        xAxis.labelCount = 7


        //Part3
        val vl = LineDataSet(entries, "Progress")

        //Part4
//        vl.setDrawValues(true)
        vl.setDrawFilled(true)
        //change colors here
        val dateFormatter = SimpleDateFormat("MM/dd")
        var lastDate = ""
        val formatter = object :  ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val date = dateFormatter.format(Date(value.toLong()))
                if (date==lastDate){
                    return ""
                }
                lastDate = date
                return date
            }
        }

        xAxis.valueFormatter = formatter

//        xAxis.setCenterAxisLabels(true)
        vl.setColor(Color.parseColor("#0000D1"))
        vl.lineWidth = 5f
        vl.circleRadius = 9f

        //Part5
//        lineChart.backgroundTintBlendMode

//        lineChart.xAxis.labelRotationAngle = 0f


        //Part6
        lineChart.data = LineData(vl)

        //Part7
        lineChart.axisRight.isEnabled = false
        lineChart.xAxis.setDrawGridLines(false)
        lineChart.axisLeft.setDrawGridLines(false)
        lineChart.axisRight.setDrawGridLines(false)

//        lineChart.xAxis.axisMaximum = 0.1f

        //Part8
        lineChart.setTouchEnabled(true)
        lineChart.setPinchZoom(true)

        //Part9
        lineChart.description.text = "Week"
        lineChart.setNoDataText("No forex yet!")

        //Part10
        lineChart.animateX(1500, Easing.EaseInExpo)

        //Part11
//        val markerView = CustomMarker(this@ShowForexActivity, R.layout.marker_view)
//        lineChart.marker = markerView


        userName = findViewById(R.id.username_text)
        userName!!.text = "${userName!!.text}${UserInfo.getUsername()}"

        bottomNavigationView = findViewById(R.id.bottom_navigation_main)
        bottomNavigationView!!.selectedItemId = R.id.action_profile
        bottomNavigationView!!.menu.findItem(R.id.action_profile).isEnabled = false
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_setting -> {
                val intent = Intent(this@MainActivity, SettingActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private val mOnNavigationItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.action_calendar -> {
                    val intent = Intent(this@MainActivity, CalendarActivity::class.java)
                    startActivity(intent)
                }
                R.id.action_workout -> {
                    val intent = Intent(this@MainActivity, BetterEx::class.java)
                    startActivity(intent)
                }
            }
            false
        }


}

//class DateAxisValueFormatter  : IAxisValueFormatter {
//
//
//    private lateinit var mValues: Array<String>
//
//    var sdf = SimpleDateFormat("yyyy.MM.dd.hh")
//
//    fun DateAxisValueFormatter(values: Array<String>) {
//        mValues = values
//    }
//
//
//    override fun getFormattedValue(value: Float, axis: AxisBase?): String? {
//        // "value" represents the position of the label on the axis (x or y)
//        return mValues[value.toInt()]
//    }
//}



