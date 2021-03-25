package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {
    private var signInButton: Button? = null
    private var signUpButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        signInButton = findViewById(R.id.sign_in)
        signUpButton = findViewById(R.id.sign_up)

        signInButton!!.setOnClickListener {
            signIn()
        }

        signUpButton!!.setOnClickListener {
            signUp()
        }

        // dummy button to search bar

        var dummy = findViewById<Button>(R.id.dummy_button)

        dummy.setOnClickListener {
            val intent = Intent(this@HomeActivity, InfoActivity::class.java)
            startActivity(intent)
        }


    }

    private fun signIn() {
        val intent = Intent(this@HomeActivity, SignInActivity::class.java)
        startActivity(intent)
    }

    private fun signUp() {
        val intent = Intent(this@HomeActivity, SignUpActivity::class.java)
        startActivity(intent)
    }

}