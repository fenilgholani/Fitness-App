package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class InfoActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private var list: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    var bottomNavigationView : BottomNavigationView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        listView = findViewById(R.id.exercise_list)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        list.add("Planks")
        list.add("Pull up")
        list.add("Push up")
        list.add("Sit up")
        list.add("Squats")

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, _, position, _ ->
            val element = parent.getItemAtPosition(position) as String // The item that was clicked
            val intent = Intent(this, ExerciseInfo::class.java)
            intent.putExtra("exerciseName",element)
            startActivity(intent)
        }

        bottomNavigationView = findViewById(R.id.bottom_navigation_info)
//        bottomNavigationView!!.selectedItemId = R.id.action_information
        bottomNavigationView!!.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.action_profile -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.action_calendar -> {
                val intent = Intent(this, CalendarActivity::class.java)
                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
            R.id.action_workout -> {
                val intent = Intent(this, TodayExerciseActivity::class.java)
                startActivity(intent)
//                return@OnNavigationItemSelectedListener true
            }
//            R.id.action_information -> {
//                val intent = Intent(this, InfoActivity::class.java)
//                startActivity(intent)
////                return@OnNavigationItemSelectedListener true
//            }
        }
        false
    }
}

//create separate activities for now


//  creating a class would help




//
//        val search = findViewById<SearchView>(R.id.searchView)
//        val listView = findViewById<ListView>(R.id.listView)
//
//        val names = arrayOf("Push up", "Pull Up", "Sit up", "Squats")
//
//        val adapter: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_list_item_1, names)
//
//        listView.adapter = adapter
//
//        search.setOnQueryTextListener(object :SearchView.OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                search.clearFocus()
//
//                if(names.contains(query))
//                    adapter.filter.filter(query)
//                else
//                    Toast.makeText(applicationContext,"Item not found", Toast.LENGTH_SHORT).show()
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapter.filter.filter(newText)
//                return false
//            }
//        })

