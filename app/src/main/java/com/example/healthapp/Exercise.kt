package com.example.healthapp

import android.util.Log
import java.sql.Timestamp
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap


//{ Date => { ExerciseName => { Set => [Weight, Reps]   } }}

//HashMap<String date, HashMap<String, HashMap<Int, ArrayList<Int>>>>


class Exercise {

    companion object{
        private var exerciseData = HashMap<String, HashMap<Int, ArrayList<Int>>>()
        private var timestamp: String ?= null


        fun setExerciseData(exerciseData: HashMap<String, HashMap<Int, ArrayList<Int>>>, timestamp: String){

            for((k,v) in exerciseData){
                if(v.isEmpty()){
                    exerciseData.remove(k)
                }
            }

            if(this.timestamp == null || timestamp.compareTo(this.timestamp!!) != 0) {
                this.timestamp = timestamp
                this.exerciseData = exerciseData
            }
            else if(timestamp.compareTo(this.timestamp!!) == 0)
                this.exerciseData.putAll(exerciseData)

        }

        fun getExerciseData(): HashMap<String, HashMap<Int, ArrayList<Int>>>{
            return exerciseData
        }
    }




}
//class Exercise(exerciseName: String, exerciseCal: Double, exerciseInfo: String, exerciseImg:String, isTimer: Boolean) {

////    Instance Variables with getters and setters
//    private var exerciseName: String? = null
//    set(value){ field = value}
//    get() = field
//    private var exerciseInfo: String? = null
//    set(value){ field = value}
//    get() = field
//    private var exerciseImg: String? = null
//    set(value){ field = value}
//    get() = field
//    private var exerciseCal: Double = 0.0
//    set(value){ field = value}
//    get() = field
//    private var isTimer :Boolean = false
////    set up the timer getter and setters
//    private var timer: String? = null
//        set(value){ field = value}
//        get() = field
//
//    //    Constructor
//    init{
//        this.exerciseName = exerciseName
//        this.exerciseCal = exerciseCal
//        this.exerciseInfo = exerciseInfo
//        this.exerciseImg = exerciseImg
//        this.isTimer = isTimer
//    }
//
//
//

//}