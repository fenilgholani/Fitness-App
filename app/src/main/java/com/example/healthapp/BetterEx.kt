package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BetterEx : AppCompatActivity()  {
    private var titlesList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imageList = mutableListOf<Int>()
    private var rv_recyclerView: RecyclerView?= null
    private var toTodayExercise: Button ?= null
    private var listSelectedEx : ArrayList<String> ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_exercise)

        rv_recyclerView = findViewById(R.id.rv_recyclerView)
        toTodayExercise = findViewById(R.id.button_to_exercisein)
        postToList()

        rv_recyclerView!!.layoutManager = LinearLayoutManager(this)
        var adapter = ItemAdapter(titlesList, descList, imageList)
        rv_recyclerView!!.adapter = adapter

        listSelectedEx = adapter.getExerciseList()

        toTodayExercise!!.setOnClickListener{
            if(listSelectedEx!!.isNotEmpty()) {
                var intent = Intent(this, TodayExerciseActivity::class.java)
                intent.putExtra("exerciseName", listSelectedEx)
                startActivity(intent)
            } else {
                Toast.makeText(
                    this,
                    "Please select at least one exercise!",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

    }

    private fun addToList(title: String, desc: String, image: Int){
        titlesList.add(title)
        descList.add(desc)
        imageList.add(image)
    }

    private fun postToList(){


//        var ex = listOf("Planks","Pull up", "Push up", "Sit up", "Squats")

        var ex = listOf("Planks","Pull up", "Push up", "Sit up", "Squats"
            ,"Oblique twist",
              "Benchpress",
              "Deadlift",
              "Tricep pulldown",
              "Pulley crunch",
              "Floor crunch",
              "Overhead tricep extension",
              "Dumbbell press",
              "Forearm curl back "  ,
              "Forearm curl in"  ,
              "Seated pulley row"  ,
              "Shrug"  ,
              "Calf raise"  ,
              "Dumbbell romanian deadlift"   ,
              "Dumbbell squat"  ,
              "Front raise"  ,
              "Julian tricep extension"  ,
              "Bicep curl"  ,
              "Dumbbell incline press"  ,
              "Side raise"  ,
              "Hand grippers"  ,
              "Dumbbell shoulder press"  ,
              "Overhead press"  ,
              "Hamstring curl"  ,
              "Lat pulldown"  ,
              "Hanging leg raise")

        var j= 1
        for(x in ex){
            addToList(x, "", R.drawable.ic_workout)
            j+=1
        }


    }


}