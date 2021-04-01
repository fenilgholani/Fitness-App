package com.example.healthapp

class Exercise(exerciseName: String, exerciseCal: Double, exerciseInfo: String, exerciseImg:String, isTimer: Boolean) {

//    Instance Variables with getters and setters
    private var exerciseName: String? = null
    set(value){ field = value}
    get() = field
    private var exerciseInfo: String? = null
    set(value){ field = value}
    get() = field
    private var exerciseImg: String? = null
    set(value){ field = value}
    get() = field
    private var exerciseCal: Double = 0.0
    set(value){ field = value}
    get() = field
    private var isTimer :Boolean = false
//    set up the timer getter and setters
    private var timer: String? = null
        set(value){ field = value}
        get() = field

    //    Constructor
    init{
        this.exerciseName = exerciseName
        this.exerciseCal = exerciseCal
        this.exerciseInfo = exerciseInfo
        this.exerciseImg = exerciseImg
        this.isTimer = isTimer
    }





}