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
                    description = context.resources.getString(R.string.pull_up)
                }
                "Plank" -> {
                    drawable = R.drawable.plank
                    description = context.resources.getString(R.string.plank)
                }
                "Squat" -> {
                    drawable = R.drawable.squat
                    description = context.resources.getString(R.string.squat)
                }
                "Push up" -> {
                    drawable = R.drawable.pushup
                    description = context.resources.getString(R.string.push_up)
                }
                "Sit up" -> {
                    drawable = R.drawable.situp
                    description = context.resources.getString(R.string.sit_up)
                }
                "Oblique Twist" -> {
                    drawable = R.drawable.oblique_twist
                    description = context.resources.getString(R.string.oblique_twist)
                }
                "Bench Press" -> {
                    drawable = R.drawable.bench_press
                    description = context.resources.getString(R.string.bench_press)
                }
                "Deadlift" -> {
                    drawable = R.drawable.deadlift
                    description = context.resources.getString(R.string.deadlift)
                }
                "Tricep Pulldown" -> {
                    drawable = R.drawable.tricep_pulldown
                    description = context.resources.getString(R.string.tricep_pulldown)
                }
                "Pulley Crunch" -> {
                    drawable = R.drawable.pulley_crunch
                    description = context.resources.getString(R.string.pulley_crunch)
                }
                "Floor Crunch" -> {
                    drawable = R.drawable.floor_crunch
                    description = context.resources.getString(R.string.floor_crunch)
                }
                "Overhead Tricep Extension" -> {
                    drawable = R.drawable.overhead_tricep_extension
                    description = context.resources.getString(R.string.overhead_tricep_extension)
                }
                "Seated Pulley Row"   -> {
                    drawable = R.drawable.seated_pulley_row
                    description = context.resources.getString(R.string.seated_pulley_row)
                }
                "Shrug"   -> {
                    drawable = R.drawable.shrug
                    description = context.resources.getString(R.string.shrug)
                }
                "Calf Raise"   -> {
                    drawable = R.drawable.calf_raise
                    description = context.resources.getString(R.string.calf_raise)
                }
                "Romanian Deadlift"  -> {
                    drawable = R.drawable.romanian_deadlift
                    description = context.resources.getString(R.string.romanian_deadlift)
                }
                "Front Raise"   -> {
                    drawable = R.drawable.front_raise
                    description = context.resources.getString(R.string.front_raise)
                }
                "Bicep Curl"   -> {
                    drawable = R.drawable.bicep_curl
                    description = context.resources.getString(R.string.bicep_curl)
                }
                "Incline press"   -> {
                    drawable = R.drawable.incline_press
                    description = context.resources.getString(R.string.incline_press)
                }
                "Side Raise"   -> {
                    drawable = R.drawable.side_raise
                    description = context.resources.getString(R.string.side_raise)
                }
                "Hand Grippers"   -> {
                    drawable = R.drawable.hand_grippers
                    description = context.resources.getString(R.string.hand_grippers)
                }
                "Shoulder Press"   -> {
                    drawable = R.drawable.shoulder_press
                    description = context.resources.getString(R.string.shoulder_press)
                }
                "Overhead Press"   -> {
                    drawable = R.drawable.overhead_press
                    description = context.resources.getString(R.string.overhead_press)
                }
                "Hamstring Curl"   -> {
                    drawable = R.drawable.hamstring_curl
                    description = context.resources.getString(R.string.hamstring_curl)
                }
                "Lat Pulldown"   -> {
                    drawable = R.drawable.lat_pulldown
                    description = context.resources.getString(R.string.lat_pulldown)
                }
                "Hanging Leg Raise" -> {
                    drawable = R.drawable.hanging_leg_raise
                    description = context.resources.getString(R.string.hanging_leg_raise)
                }
            }

            FancyGifDialog.Builder(context as Activity?)
                .setTitle(holder.itemTitle.text.toString())
                .setMessage(description)
                .setNegativeBtnText("Cancel")
//                .setNegativeBtnBackground("#E43F5A")
                .setPositiveBtnText("Add")
                .setGifResource(drawable!!)
                .isCancellable(false)
                .OnPositiveClicked {
                    holder.itemCheckBox.isChecked = true
                    Toast.makeText(context, "${holder.itemTitle.text} Added!", Toast.LENGTH_SHORT).show()
                }.OnNegativeClicked {
                    // Do Nothing
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
    }

    override fun getItemCount(): Int {
        return titles.size
    }


    fun getExerciseList(): ArrayList<String> {
        return exerciseList
    }



}

