package com.example.healthapp

import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R

class ItemAdapter(private var titles: List<String>, private var details: List<String>, private var images:List<Int>) :

    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            val itemTitle: TextView = itemView.findViewById(R.id.ex_title)
            val itemDetail: TextView = itemView.findViewById(R.id.ex_description)
            val itemImg: ImageView = itemView.findViewById(R.id.ex_pic)

            init {

                itemView.setOnClickListener{
                    val position: Int = adapterPosition
                    Toast.makeText(itemView.context, "You clicked on item ${position+1}", Toast.LENGTH_SHORT).show()
                }
            }

//            fun click(View: view ){
//
//            }

        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.row_exercise, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImg.setImageResource(images[position])
    }

    override fun getItemCount(): Int {
        return titles.size
    }


}