package com.example.healthapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class BetterEx : AppCompatActivity() {
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

        for (i in 1..15) {
            addToList("Item $i", "Description $i", R.drawable.ic_workout)
        }
    }

}