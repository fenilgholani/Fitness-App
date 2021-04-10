package com.example.healthapp

class DateExercise {
    companion object {
        private var dateExercise = HashMap<String, HashMap<String, HashMap<Int, ArrayList<Int>>>>()


        fun setExerciseData(dateExercise: HashMap<String, HashMap<String, HashMap<Int, ArrayList<Int>>>>) {
            this.dateExercise = dateExercise
        }

        fun getExerciseData(): HashMap<String, HashMap<String, HashMap<Int, ArrayList<Int>>>> {
            return dateExercise
        }
    }
}