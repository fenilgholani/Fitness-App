package com.example.healthapp

import android.content.Intent
import android.graphics.Color
import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.*
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private var bottomNavigationView: BottomNavigationView? = null
    private var userName: TextView? = null


    var barChart: BarChart? = null

    // variable for our bar data set.
    var barDataSet1: BarDataSet? = null
    var barDataSet2: BarDataSet? = null


    // array list for storing entries.
    var barEntries: ArrayList<BarEntry>? = null

    // creating a string array for displaying days.
    var days: ArrayList<String> ?=null

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

        // CALORIE CALCULATOR

        val default_cal = 10

        val df: DateFormat = SimpleDateFormat("MM.dd.yyyy")
        val date: String = df.format(Calendar.getInstance().time)
        val f = SimpleDateFormat("MM.dd.yyyy")
        val d = f.parse(date)
        var milliseconds = d.time
        Log.i("today date", milliseconds.toString())

        val exercise = DateExercise.getExerciseData()
        var total_cal = 0

        var allExercise = exercise[date]

        if (allExercise != null) {
            for((k,v) in allExercise){

                for((set, weightReps) in v){

                    total_cal+=default_cal
                }

            }
        }
//        var dat = Calendar.getInstance()   // 19-01-2018
//        dat.add(Calendar.DATE, -7)

        days!!.add(getDaysAgo(0).month.toString()+"/"+getDaysAgo(7).day.toString())
        days!!.add(getDaysAgo(1).month.toString()+"/"+getDaysAgo(7).day.toString())
        days!!.add(getDaysAgo(2).month.toString()+"/"+getDaysAgo(7).day.toString())
        days!!.add(getDaysAgo(3).month.toString()+"/"+getDaysAgo(7).day.toString())
        days!!.add(getDaysAgo(4).month.toString()+"/"+getDaysAgo(7).day.toString())
        days!!.add(getDaysAgo(5).month.toString()+"/"+getDaysAgo(7).day.toString())
        days!!.add(getDaysAgo(6).month.toString()+"/"+getDaysAgo(7).day.toString())
        Log.i("All the days", getDaysAgo(7).month.toString())

        barChart = findViewById(R.id.barchart)

        // creating a new bar data set.

        barDataSet1 = BarDataSet(getBarEntriesOne(), "First Set")
        barDataSet1!!.color = applicationContext.resources.getColor(R.color.purple_200)
//        barDataSet2 = BarDataSet(getBarEntriesTwo(), "Second Set")
//        barDataSet2!!.setColor(Color.BLUE)

        // below line is to add bar data set to our bar data.

        // below line is to add bar data set to our bar data.
        val data = BarData(barDataSet1)

        // after adding data to our bar data we
        // are setting that data to our bar chart.

        // after adding data to our bar data we
        // are setting that data to our bar chart.
        barChart!!.setData(data)

        // below line is to remove description
        // label of our bar chart.

        // below line is to remove description
        // label of our bar chart.
        barChart!!.getDescription().isEnabled = false

        // below line is to get x axis
        // of our bar chart.

        // below line is to get x axis
        // of our bar chart.
        val xAxis = barChart!!.getXAxis()

        barChart!!.setFitBars(true)
        // below line is to set value formatter to our x-axis and
        // we are adding our days to our x axis.

        // below line is to set value formatter to our x-axis and
        // we are adding our days to our x axis.
        xAxis.valueFormatter = IndexAxisValueFormatter(days)

        // below line is to set center axis
        // labels to our bar chart.

        // below line is to set center axis
        // labels to our bar chart.
        xAxis.setCenterAxisLabels(false)

        // below line is to set position
        // to our x-axis to bottom.

        // below line is to set position
        // to our x-axis to bottom.
        xAxis.position = XAxis.XAxisPosition.BOTTOM

        // below line is to set granularity
        // to our x axis labels.

        // below line is to set granularity
        // to our x axis labels.
        xAxis.granularity = 1f

        // below line is to enable
        // granularity to our x axis.

        // below line is to enable
        // granularity to our x axis.
        xAxis.isGranularityEnabled = true

        // below line is to make our
        // bar chart as draggable.

        // below line is to make our
        // bar chart as draggable.
        barChart!!.setDragEnabled(true)

        // below line is to make visible
        // range for our bar chart.

        // below line is to make visible
        // range for our bar chart.
//        barChart!!.setVisibleXRangeMaximum(3f)

        // below line is to add bar
        // space to our chart.

        // below line is to add bar
        // space to our chart.
        val barSpace = 0.1f

        // below line is use to add group
        // spacing to our bar chart.

        // below line is use to add group
        // spacing to our bar chart.
        val groupSpace = 0.5f

        // we are setting width of
        // bar in below line.

        // we are setting width of
        // bar in below line.
        data.barWidth = 0.8f

        // below line is to set minimum
        // axis to our chart.

        // below line is to set minimum
        // axis to our chart.
        barChart!!.getXAxis().axisMinimum = 0f

        // below line is to
        // animate our chart.

        // below line is to
        // animate our chart.
        barChart!!.animate()

        // below line is to group bars
        // and add spacing to it.

        // below line is to group bars
        // and add spacing to it.

        // below line is to invalidate
        // our bar chart.

        // below line is to invalidate
        // our bar chart.
        barChart!!.invalidate()

//
//        var lineChart: LinceChart = findViewById(R.id.lineChart)
//
//
//        //Part1
//        val entries = ArrayList<Entry>()
//
//
//
//
//        //Part2
//        entries.add(Entry(1619150400000f, 100f))
//        entries.add(Entry(1619236800000f, 200f))
//        entries.add(Entry(1619323200000f, 700f))
//        entries.add(Entry(1619409600000f, 200f))
//        entries.add(Entry(1619582400000f, 520f))
//        entries.add(Entry(1619668800000f, 360f))
////        entries.add(Entry(milliseconds.toFloat(), total_cal.toFloat()))
//
//
//        val xAxis: XAxis = lineChart.xAxis
//        xAxis.position = XAxis.XAxisPosition.BOTTOM
//        val vl = LineDataSet(entries, "Progress")
//        vl.setDrawFilled(true)
//        val dateFormatter = SimpleDateFormat("dd/MM")
//        var lastDate = ""
//        val formatter = object :  ValueFormatter() {
//            override fun getFormattedValue(value: Float): String {
//                val date = dateFormatter.format(Date(value.toLong()))
//                if (date==lastDate){
//                    return ""
//                }
//                lastDate = date
//                return date
//            }
//        }
//
//        xAxis.valueFormatter = formatter
//        vl.color = Color.parseColor("#0000D1")
//        vl.lineWidth = 3f
////        vl.circleRadius = 6f
//        lineChart.data = LineData(vl)
//
//        //Part7
//        lineChart.axisRight.isEnabled = false
//        lineChart.xAxis.setDrawGridLines(false)
//        lineChart.axisLeft.setDrawGridLines(false)
//        lineChart.axisRight.setDrawGridLines(false)
//        lineChart.setTouchEnabled(true)
//        lineChart.setPinchZoom(true)
////        lineChart.xAxis.axisMaximum = j+0.1f
//        lineChart.description.text = "Week"
//        lineChart.setNoDataText("No forex yet!")
//        lineChart.animateX(1500, Easing.EaseInExpo)



        userName = findViewById(R.id.username_text)
        userName!!.text = "${userName!!.text}${UserInfo.getUsername()}!"

        bottomNavigationView = findViewById(R.id.bottom_navigation_main)
        bottomNavigationView!!.selectedItemId = R.id.action_profile
        bottomNavigationView!!.menu.findItem(R.id.action_profile).isEnabled = false
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)

    }

    // array list for first set
    private fun getBarEntriesOne(): ArrayList<BarEntry>? {

        // creating a new array list
        barEntries = ArrayList<BarEntry>()

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntries!!.add(BarEntry(0f, 9f))
        barEntries!!.add(BarEntry(1f, 4f))
        barEntries!!.add(BarEntry(2f, 6f))
        barEntries!!.add(BarEntry(3f, 8f))
        barEntries!!.add(BarEntry(4f, 2f))
        barEntries!!.add(BarEntry(5f, 4f))
        barEntries!!.add(BarEntry(6f, 1f))

        return barEntries
    }

    fun getDaysAgo(daysAgo: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_YEAR, -daysAgo)

        return calendar.time
    }

    private fun getBarEntriesTwo(): ArrayList<BarEntry>? {

        // creating a new array list
        barEntries = ArrayList()

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntries!!.add(BarEntry(1f, 8f))
        barEntries!!.add(BarEntry(2f, 12f))
        barEntries!!.add(BarEntry(3f, 4f))
        barEntries!!.add(BarEntry(4f, 1f))
        barEntries!!.add(BarEntry(5f, 7f))
        barEntries!!.add(BarEntry(6f, 3f))
        return barEntries
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


