package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dynamic_exercise)

        rv_recyclerView = findViewById(R.id.rv_recyclerView)
        postToList()

        rv_recyclerView!!.layoutManager = LinearLayoutManager(this)
        rv_recyclerView!!.adapter = ItemAdapter(titlesList, descList, imageList)





    }

    private fun addToList(title: String, desc: String, image: Int){
        titlesList.add(title)
        descList.add(desc)
        imageList.add(image)
    }

    private fun postToList(){


        var ex = listOf("Oblique twist",
              "Benchpress",
              "Deadlift",
              "Squat",
              "Tricep pulldown",
              "Pulley crunch",
              "Floor crunch",
              "Overhead tricep extension",
              "Dumbbell press",
              "Forearm curl back (15 reps)"  ,
              "Forearm curl in (15 reps)"  ,
              "Seated pulley row"  ,
              "Shrug"  ,
              "Calf raise"  ,
              "Dumbbell romanian deadlift"  ,
              "Forearm curl up (25 reps)"  ,
              "Dumbbell squat"  ,
              "Front raise"  ,
              "Julian tricep extension"  ,
              "Bicep curl"  ,
              "Dumbbell incline press"  ,
              "Hammer pulley curls 2h"  ,
              "Side raise"  ,
              "Hand grippers"  ,
              "Dumbbell shoulder press"  ,
              "Overhead press"  ,
              "Hamstring curl"  ,
              "Lat pulldown"  ,
              "Hanging leg raise").sorted()

        var j= 1
        for(x in ex){
            addToList(x, "Description $j", R.drawable.ic_workout)
            j+=1
        }


    }


}