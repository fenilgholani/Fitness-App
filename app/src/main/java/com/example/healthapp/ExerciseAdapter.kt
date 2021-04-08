package com.example.healthapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ExerciseAdapter(
    private var exerciseNames: ArrayList<String> // list of selected exercises for the day
) :

RecyclerView.Adapter<ExerciseAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val itemTitle: TextView = itemView.findViewById(R.id.today_exercise_name)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_todaysrow,
            parent,
            false
        )

        var default = ArrayList<Int>()
        for(i in 0..3) {
            default.add(0)
        }

        var rowRecyclerView = v.findViewById<RecyclerView>(R.id.row_recyclerView)
        rowRecyclerView!!.layoutManager = LinearLayoutManager(v.context)
        var adapter = RowAdapter(default)
        rowRecyclerView!!.adapter = adapter

        val add = v.findViewById<Button>(R.id.add_set)
        add.setOnClickListener {
            default.add(0)
            adapter.notifyDataSetChanged()
        }

        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = exerciseNames[position]
    }

    override fun getItemCount(): Int {
        return exerciseNames.size
    }


}

