package com.example.healthapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView

class CalendarAdapter(
    private var exerciseData :HashMap<String, HashMap<Int, java.util.ArrayList<Int>>> ):

    RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView= itemView.findViewById(R.id.calendar_exerciseName)
        val itemSet: TextView = itemView.findViewById(R.id.calendar_set)
        val itemWeight: TextView = itemView.findViewById(R.id.calendar_lb)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_exercise,
            parent,
            false
        )
        return  ViewHolder(v)
    }

    //  ExerciseName => [lbs, ]
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Log.i("Information", exerciseData.toString())


    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }




}

