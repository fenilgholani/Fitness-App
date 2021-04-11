package com.example.healthapp

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.shashank.sony.fancygifdialoglib.FancyGifDialog


class ItemAdapter(
    private var titles: List<String>,
    private var details: List<String>,
    private var images: List<Int>
) :

    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    private var exerciseList = ArrayList<String>()


        inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

            val itemTitle: TextView = itemView.findViewById(R.id.ex_title)
//            val itemDetail: TextView = itemView.findViewById(R.id.ex_description)
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
//        holder.itemDetail.text = details[position]
        holder.itemImg.setImageResource(images[position])

        holder.itemView.setOnClickListener {
            var context = holder.itemTitle.context
            var drawable: Int? = null
            var description: String? = null

            when(holder.itemTitle.text){
                "Pull up" -> {
                    drawable = R.drawable.pullup
                    description = context.resources.getString(R.string.pullup)
                }
                "Plank" -> {
                    drawable = R.drawable.plank
                }
                "Squat" -> {
                    drawable = R.drawable.squat
                }
                "Push up" -> {
                    drawable = R.drawable.pushup
                }
                "Sit up" -> {
                    drawable = R.drawable.situp
                }
            }

            FancyGifDialog.Builder(context as Activity?)
                .setTitle(holder.itemTitle.text.toString())
                .setMessage(description)
                .setNegativeBtnText("Cancel")
                .setNegativeBtnBackground("#E43F5A")
                .setPositiveBtnText("Ok")
                .setGifResource(drawable!!)
                .isCancellable(false)
                .OnPositiveClicked {
                    holder.itemCheckBox.isChecked = true
                    Toast.makeText(context, "${holder.itemTitle.text} Added!", Toast.LENGTH_SHORT).show()
                }.OnNegativeClicked {

                }
                .build()

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

