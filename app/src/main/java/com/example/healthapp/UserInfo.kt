package com.example.healthapp

import de.hdodenhof.circleimageview.CircleImageView
import java.util.*
import kotlin.collections.HashMap

class UserInfo {
    companion object {
        private var username: String? = null
        private var userInfo = HashMap<String, String>() // email, password
        private var heightFt: String? = null
        private var heightIn: String? = null// ft, inches
        private var weight: String?= null
        private var weightUn: String?= null// num, unit
        private var dob: Date? = null
        private var avatar: CircleImageView? = null

        fun setUsername(username: String) {
            this.username = username
        }

        fun getUsername(): String? {
            return username
        }

        fun setUserData(userInfo: HashMap<String, String>) {
            this.userInfo = userInfo
        }

        fun getUserData(): HashMap<String, String> {
            return userInfo
        }

        fun setHeightFt(heightFt: String){
            this.heightFt = heightFt
        }

        fun setHeightIn(heightIn: String){
            this.heightIn = heightIn
        }

        fun getHeightFt(): String? {
           return heightFt
        }

        fun getHeightIn(): String? {
            return heightIn
        }


        fun setWeight(weight: String){
            this.weight = weight
        }

        fun getWeight(): String ?{
            return weight
        }

        fun setWeightUn(weightUn: String){
            this.weightUn = weightUn
        }

        fun getWeightUn(): String ?{
            return weightUn
        }


        fun setDOB(dob: Date){
            this.dob = dob
        }

        fun getDOB(): Date? {
            return dob
        }

        fun setAvatar(avatar: CircleImageView){
            this.avatar = avatar
        }

        fun getAvatar(): CircleImageView? {
            return avatar
        }
    }
}