package com.example.healthapp

import android.util.Log
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

    private var exerciseHash = HashMap<String, HashMap<Int, ArrayList<Int>>>()
    private var completeButtonSend = ArrayList<Button>()

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
        rowRecyclerView.adapter = adapter


        val add = v.findViewById<Button>(R.id.add_set)
        val completed = v.findViewById<Button>(R.id.completed)
        val delete = v.findViewById<Button>(R.id.delete_exercise)
        val exerciseData = v.findViewById<TextView>(R.id.today_exercise_name)


        add.setOnClickListener {
            default.add(0)
            adapter.exerciseWeight[default.size] = 0
            adapter.exerciseRep[default.size] = 0
            Log.i(" Added", "Weight:${adapter.exerciseWeight} Rep:${adapter.exerciseRep}")
            adapter.notifyItemInserted(default.size)
//            adapter.notifyItemChanged(default.size)
//            adapter.notifyItemChanged(default.size+1)
        }

        delete.setOnClickListener {
            Toast.makeText(
                v.context, "Delete Exercise Under Development!",
                Toast.LENGTH_LONG
            ).show()
        }

        completed.setOnClickListener {

            exerciseHash[exerciseData.text.toString()] = adapter.getSets()

        }

        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = exerciseNames[position]

        setCompleteButton(holder.itemView.findViewById<Button>(R.id.completed))

    }

    override fun getItemCount(): Int {
        return exerciseNames.size
    }


    fun getexerciseData(): HashMap<String, HashMap<Int, ArrayList<Int>>> {
        return exerciseHash
    }

    fun getCompleteButton(): ArrayList<Button>? {

        return this.completeButtonSend
    }

    private fun setCompleteButton( b: Button){

        if(!this.completeButtonSend.contains(b))
            this.completeButtonSend.add(b)
    }

}

