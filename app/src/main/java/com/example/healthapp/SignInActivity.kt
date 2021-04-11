package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignInActivity : AppCompatActivity() {
    private var userEmail: EditText? = null
    private var userPassword: EditText? = null
    private var signInButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        userEmail = findViewById(R.id.email)
        userPassword = findViewById(R.id.password)
        signInButton = findViewById(R.id.sign_in)
//        progressBar = findViewById(R.id.progressBar)

        signInButton!!.setOnClickListener {
            signInUserAccount()
        }

        for((k,v) in UserInfo.getUserData())
            Log.i("SIGNING", "$k $v")
    }

    private fun signInUserAccount() {
//        progressBar?.visibility = View.VISIBLE

        val email: String = userEmail?.text.toString()
        val password: String = userPassword?.text.toString()

        // Ensure fields aren't empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(applicationContext, "Please enter email...", Toast.LENGTH_LONG).show()
//            progressBar?.visibility = View.INVISIBLE
            return
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(applicationContext, "Please enter password!", Toast.LENGTH_LONG).show()
//            progressBar?.visibility = View.INVISIBLE
            return
        }

        // Sign in User
        if (verifyUser()) {
            Toast.makeText(applicationContext, "Sign in successful!", Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this@SignInActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                applicationContext,
                "Sign in failed! Please check Email and/or Password.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun verifyUser(): Boolean{
        var verified = false
        for((k,v) in UserInfo.getUserData()) {
            if (k == userEmail!!.text.toString() && v == userPassword!!.text.toString()) {
                verified = true
            }
        }
        return verified
    }

}