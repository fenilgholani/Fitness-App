package com.example.healthapp

import android.text.Editable
import android.text.TextWatcher
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

    private val exerciseRep: HashMap<Int, Int> = HashMap()
    private val exerciseWeight: HashMap<Int, Int> = HashMap()
    private var isOnTextChanged: Boolean = false
    private val pos: Int = 0

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.set_num)
        val itemWeight: TextView = itemView.findViewById(R.id.weight_done)
        val itemReps: TextView = itemView.findViewById(R.id.reps_done)
        val itemClose: Button = itemView.findViewById(R.id.close_set)

        init{
            itemClose.setOnClickListener {

                if(defaultRows.size !=1) {

//                    exerciseWeight.remove(adapterPosition+1)
//                    exerciseRep.remove(adapterPosition+1)
//
//                    for(i in adapterPosition..(itemCount + 1)){
//                        exerciseRep[i-1] = exerciseRep[i]
//                        exerciseRep[i-1] = exerciseRep[i]
//
//                    }

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

        holder.itemReps.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if(isOnTextChanged){
                    isOnTextChanged = false

                    try{
                            exerciseRep[position+1] = s.toString().toInt()

                    }catch (e: NumberFormatException){
                        Toast.makeText(holder.itemView.context, "Invalid input", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isOnTextChanged = true
            }
        })


        holder.itemWeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if(isOnTextChanged){
                    isOnTextChanged = false

                    try{
                        exerciseWeight[position+1] = s.toString().toInt()

                    }catch (e: NumberFormatException){
                        Toast.makeText(holder.itemView.context, "Invalid input", Toast.LENGTH_SHORT).show()
                    }
                }

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                isOnTextChanged = true
            }
        })


    }

    override fun getItemCount(): Int {
        return defaultRows.size
    }

    fun getSets(): HashMap<Int, ArrayList<Int>>{

        var hash = HashMap<Int, ArrayList<Int>>()
        var list = ArrayList<Int>()

        for(i in 1..itemCount) {

            exerciseWeight[i]?.let { list.add(it) }
            exerciseRep[i]?.let { list.add( it) }

            if(!list.isEmpty()) {

                hash[i] = list

                list = ArrayList<Int>()
            }


        }
        return hash

    }

}

