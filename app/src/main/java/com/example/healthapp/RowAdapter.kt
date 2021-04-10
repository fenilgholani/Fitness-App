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
import java.lang.reflect.TypeVariable


class RowAdapter(
    private var defaultRows: ArrayList<Int>, // list of selected exercises for the day
    private var exerciseRep: HashMap<Int, Int>,
    private var exerciseWeight: HashMap<Int, Int>
) :

    RecyclerView.Adapter<RowAdapter.ViewHolder>() {


    private var isOnTextChangedR: Boolean = false
    private var isOnTextChangedW: Boolean = false
    private var indexingChange: Boolean = false

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemTitle: TextView = itemView.findViewById(R.id.set_num)
        val itemWeight: TextView = itemView.findViewById(R.id.weight_done)
        val itemReps: TextView = itemView.findViewById(R.id.reps_done)
        val itemClose: Button = itemView.findViewById(R.id.close_set)

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
        holder.itemReps.text = ""
        holder.itemWeight.text = ""

        if(exerciseRep.containsKey(position+1))
            holder.itemReps.text = exerciseRep[position+1].toString()

        if(exerciseWeight.containsKey(position+1))
            holder.itemWeight.text = exerciseWeight[position+1].toString()

        //change the positioning of data when closed
        holder.itemClose.setOnClickListener{


            Log.i("Before", "Weight:$exerciseWeight Rep:$exerciseRep")

            if(defaultRows.size !=1) {

                exerciseWeight.remove(holder.adapterPosition + 1)
                exerciseRep.remove(holder.adapterPosition + 1)

                defaultRows.removeAt(holder.adapterPosition)


                notifyItemRemoved(holder.adapterPosition)


            }
            else {
                Toast.makeText(
                    holder.itemView.context,
                    "This exercise must have at least 1 set!",
                    Toast.LENGTH_SHORT
                ).show()
            }

            var flag = false

            for( i in 1..(exerciseRep.size)){

                if(!exerciseRep.containsKey(i)){
                    exerciseRep[i] = exerciseRep[i+1]!!
                    exerciseWeight[i] = exerciseWeight[i+1]!!
                    flag = true
                    continue
                }

                else if(flag){
                    exerciseRep[i] = exerciseRep[i+1]!!
                    exerciseWeight[i] = exerciseWeight[i+1]!!
                }
            }

            exerciseRep.remove(exerciseRep.size)
            exerciseWeight.remove(exerciseWeight.size)

            indexingChange = true
//
            Log.i("after", "Weight:$exerciseWeight Rep:$exerciseRep")
//
//            if(indexingChange){
//                for(i in 1..itemCount)
//                    notifyItemChanged(i)
//                indexingChange = false
//            }



            holder.itemReps.text = ""
            holder.itemWeight.text = ""

            for(i in holder.adapterPosition..(exerciseRep.size -1))
                notifyItemChanged(i)




        }





        holder.itemReps.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if(isOnTextChangedR){
                    isOnTextChangedR = false

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
                isOnTextChangedR = true
            }
        })


        holder.itemWeight.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if(isOnTextChangedW){
                    isOnTextChangedW = false

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
                isOnTextChangedW = true
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

