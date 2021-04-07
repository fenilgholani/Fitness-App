package com.example.healthapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class RowAdapter(
    private var exerciseNames: ArrayList<String> // list of selected exercises for the day
) :

    RecyclerView.Adapter<RowAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.today_exercise_name)

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_todaysrow,
            parent,
            false
        )
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = exerciseNames[position]
    }

    override fun getItemCount(): Int {
        return exerciseNames.size
    }


}

