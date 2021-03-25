package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.example.healthapp.R.array.exercise_array
import java.util.*
import kotlin.collections.ArrayList

class InfoActivity : AppCompatActivity() {

    lateinit var listView: ListView
    var list: ArrayList<String> = ArrayList()
    lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info)

        listView = findViewById(R.id.exercise_list)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)

        list.add("")
        list.add("Squats")
        list.add("Planks")
        list.add("Pull up")
        list.add("Push up")
        list.add("Sit up")

        listView.adapter = adapter

        listView.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position) as String// The item that was clicked
            val intent = Intent(this, ExerciseInfo::class.java)
            startActivity(intent)
        }
    }
}




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

