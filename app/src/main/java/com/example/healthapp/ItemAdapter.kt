package com.example.healthapp

import android.app.Dialog
import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.customview.customView
import com.bumptech.glide.Glide
import pl.droidsonroids.gif.GifImageView
import java.util.zip.Inflater


class ItemAdapter(
    private var titles: List<String>,
    private var details: List<String>,
    private var images: List<Int>
) :


    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var list_exercise = ArrayList<String>()


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
            val l = LayoutInflater.from(holder.itemView.context).inflate(R.layout.activity_dialog, null);

            var gif : GifImageView? = null

            when(holder.itemTitle.text){
                "Pull up"-> gif = l.findViewById(R.id.ex_pullup)
                "Planks"-> gif = l.findViewById(R.id.ex_plank)
            }

//            gif.setImageDrawable(ContextCompat.getDrawable(l.context, R.drawable.plank)
//            gif.setImageResource(R.drawable.plank)
//            Glide.with(l).asGif().load(R.drawable.pullup).into(gif)

            val dialog = MaterialDialog(context)
                .customView(R.layout.activity_dialog)



            dialog.show()



//            var intent = Intent(context, ExerciseInfo::class.java)
//            intent.putExtra("exerciseName", holder.itemTitle.text)
//            context.startActivity(intent)

        }

        holder.itemCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                list_exercise.add(holder.itemTitle.text.toString())

            }
            else{
                list_exercise!!.remove(holder.itemTitle.text.toString())
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
        return list_exercise
    }



}

