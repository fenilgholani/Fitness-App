package com.example.healthapp

import java.util.*
import kotlin.collections.HashMap

class UserInfo {
    companion object {
        private var username: String? = null
        private var userInfo = HashMap<String, String>() // email, password
        private var height = HashMap<String, String>() // ft, inches
        private var weight = HashMap<String, String>() // num, unit
        private var dob: Date? = null

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

        fun setHeight(height: HashMap<String, String>){
            this.height = height
        }

        fun getHeight(): HashMap<String, String>{
           return height
        }

        fun setWeight(height: HashMap<String, String>){
            this.weight = weight
        }

        fun getWeight(): HashMap<String, String>{
            return weight
        }

        fun setDOB(dob: Date){
            this.dob = dob
        }

        fun getDOB(): Date? {
            return dob
        }
    }
}