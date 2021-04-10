package com.example.healthapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import pl.droidsonroids.gif.GifImageView


class ItemAdapter(
    private var titles: List<String>,
    private var details: List<String>,
    private var images: List<Int>
) :

//Hello
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var exerciseList = ArrayList<String>()


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            val itemTitle: TextView = itemView.findViewById(R.id.ex_title)
            val itemDetail: TextView = itemView.findViewById(R.id.ex_description)
            val itemImg: ImageView = itemView.findViewById(R.id.ex_pic)
            val itemCheckBox: CheckBox = itemView.findViewById(R.id.ex_checkbox)


            init{

                itemView.setOnClickListener{
                    val pos: Int = adapterPosition
                    Toast.makeText(
                        itemView.context,
                        "You clicked on item ${pos + 1}",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v: View = LayoutInflater.from(parent.context).inflate(
            R.layout.row_exercise,
            parent,
            false
        )
        return  ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        holder.itemTitle.text = titles[position]
        holder.itemDetail.text = details[position]
        holder.itemImg.setImageResource(images[position])


        holder.itemView.setOnClickListener {

            var context = holder.itemTitle.context

            if(holder.itemTitle.text == "Planks") {
                val dialog = MaterialDialog(context)
                    .customView(R.layout.ex_activity_plank)

                dialog.show()
            }
            if(holder.itemTitle.text == "Pull up") {
                val dialog = MaterialDialog(context)
                    .customView(R.layout.ex_activity_pullup)

                dialog.show()
            }
            if(holder.itemTitle.text == "Push up") {
                val dialog = MaterialDialog(context)
                    .customView(R.layout.ex_activity_pushup)

                dialog.show()
            }
            if(holder.itemTitle.text == "Sit up") {
                val dialog = MaterialDialog(context)
                    .customView(R.layout.ex_activity_situp)

                dialog.show()
            }
            if(holder.itemTitle.text == "Squats") {
                val dialog = MaterialDialog(context)
                    .customView(R.layout.ex_activity_squat)

                dialog.show()
            }

//            var gif: ImageView = l.findViewById(R.id.ex_gif)

//            when(holder.itemTitle.text){
//                "Pull up"-> gif = l.findViewById(R.id.ex_pullup)
//                "Planks"-> gif = l.findViewById(R.id.ex_plank)
//            }


//            gif.setImageResource(R.drawable.plank)
//            Glide.with(l).asGif().load(R.drawable.pullup).into(gif)

//            var intent = Intent(context, ExerciseInfo::class.java)
//            intent.putExtra("exerciseName", holder.itemTitle.text)
//            context.startActivity(intent)

        }

        holder.itemCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                exerciseList.add(holder.itemTitle.text.toString())
            }
            else {
                exerciseList.remove(holder.itemTitle.text.toString())
            }



        }

//        context = holder.itemDetail.context
//        intent = Intent( context, InfoActivity::class.java)
//        intent.putExtra("exerciseName","Plank")
//        context.startActivity(intent)

    }

    override fun getItemCount(): Int {
        return titles.size
    }


    fun getExerciseList(): ArrayList<String> {
        return exerciseList
    }



}

