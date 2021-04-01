package com.example.healthapp

import android.content.Intent
import android.service.quicksettings.Tile
import android.telecom.Call
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.healthapp.R

class ItemAdapter(private var titles: List<String>, private var details: List<String>, private var images:List<Int>) :


    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            val itemTitle: TextView = itemView.findViewById(R.id.ex_title)
            val itemDetail: TextView = itemView.findViewById(R.id.ex_description)
            val itemImg: ImageView = itemView.findViewById(R.id.ex_pic)

            init{

                itemView.setOnClickListener{
                    val pos: Int = adapterPosition
                    Toast.makeText(itemView.context, "You clicked on item ${pos+1}", Toast.LENGTH_SHORT).show()
                }

            }

//            override fun onClick(v: View?) {
//                val position = adapterPosition
//                if (position != RecyclerView.NO_POSITION) {
//                    listener.onItemClick(position)
//                }
//            }




        }


//    interface OnItemClickListener {
//        fun onItemClick(position: Int)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(R.layout.row_exercise, parent, false)
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImg.setImageResource(images[position])

        holder.itemView.setOnClickListener {

            var context = holder.itemTitle.context
            var intent = Intent(context, ExerciseInfo::class.java)
            intent.putExtra("exerciseName", "Planks")
            context.startActivity(intent)

        }
//        context = holder.itemDetail.context
//        intent = Intent( context, InfoActivity::class.java)
//        intent.putExtra("exerciseName","Plank")
//        context.startActivity(intent)

    }

    override fun getItemCount(): Int {
        return titles.size
    }




}