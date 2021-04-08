package com.example.healthapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView


class RowAdapter(
    private var defaultRows: ArrayList<Int> // list of selected exercises for the day
) :

    RecyclerView.Adapter<RowAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.set_num)
        val itemWeight: TextView = itemView.findViewById(R.id.weight_done)
        val itemReps: TextView = itemView.findViewById(R.id.reps_done)
        val itemClose: Button = itemView.findViewById(R.id.close_set)

        init{
            itemClose.setOnClickListener {

                if(defaultRows.size !=1) {
                    defaultRows.removeAt(adapterPosition)
                    notifyDataSetChanged()
                } else {
                    Toast.makeText(
                        itemView.context,
                        "This exercise must have at least 1 set!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_set,
            parent,
            false
        )
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemTitle.text = "${position + 1}"
//        holder.itemWeight.text = ""
//        holder.itemReps.text = ""
    }

    override fun getItemCount(): Int {
        return defaultRows.size
    }

}

