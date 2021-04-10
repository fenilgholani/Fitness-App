package com.example.healthapp

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView


class RowAdapter(
    private var defaultRows: ArrayList<Int> // list of selected exercises for the day
) :

    RecyclerView.Adapter<RowAdapter.ViewHolder>() {

    private var exerciseRep: HashMap<Int, Int> = HashMap()
    private var exerciseWeight: HashMap<Int, Int> = HashMap()
    private var isOnTextChangedR: Boolean = false
    private var isOnTextChangedW: Boolean = false
    private var indexingChange: Boolean = false

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val itemSet: TextView = itemView.findViewById(R.id.set_num)
        val itemWeight: EditText = itemView.findViewById(R.id.weight_done)
        val itemReps: EditText = itemView.findViewById(R.id.reps_done)
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
        holder.itemSet.text = "${position + 1}"
        holder.itemWeight.setText("")
        holder.itemReps.setText("")


        if(exerciseRep.containsKey(position+1))
            holder.itemReps.setText(exerciseRep[position+1].toString())

        if(exerciseWeight.containsKey(position+1))
            holder.itemWeight.setText(exerciseWeight[position+1].toString())


        //change the positioning of data when closed
        holder.itemClose.setOnClickListener{
            Log.i("Before", "Weight:$exerciseWeight Rep:$exerciseRep")

            if(itemCount == 1 ) {

                Toast.makeText(
                    holder.itemView.context,
                    "This exercise must have at least 1 set!",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                exerciseWeight.remove(holder.adapterPosition + 1)
                exerciseRep.remove(holder.adapterPosition + 1)

                defaultRows.removeAt(holder.adapterPosition)
                this.notifyItemRemoved(holder.adapterPosition)
                notifyItemRangeChanged(position, defaultRows.size)

               var flag = false

                for( i in (position+1)..(exerciseRep.size)){
//                if(exerciseRep[i+1] != null) {
                    exerciseRep[i] = exerciseRep[i + 1]!!
                    exerciseWeight[i] = exerciseWeight[i + 1]!!
//                }
//                if(!exerciseRep.containsKey(i)){
//                    exerciseRep[i] = exerciseRep[i+1]!!
//                    exerciseWeight[i] = exerciseWeight[i+1]!!
//                    flag = true
//                    continue
//                }
//
//                else if(flag){
//                    Log.i("after", "Weight:$exerciseWeight Rep:$exerciseRep")
//                    exerciseRep[i] = exerciseRep[i+1]!!
//                    exerciseWeight[i] = exerciseWeight[i+1]!!
//                }
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

//                holder.itemReps.text = ""
//                holder.itemWeight.text = ""
                holder.itemWeight.setText("")
                holder.itemReps.setText("")
//                for(i in 1..itemCount)
//                    notifyItemChanged(i)
            }

        }

        holder.itemReps.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

                if(isOnTextChangedR){
                    isOnTextChangedR = false

                    try{
                        exerciseRep[position+1] = s.toString().toInt()

                    }catch (e: NumberFormatException){
//                        Toast.makeText(holder.itemView.context, "Invalid input", Toast.LENGTH_SHORT).show()
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
//                        Toast.makeText(holder.itemView.context, "Invalid input", Toast.LENGTH_SHORT).show()
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
