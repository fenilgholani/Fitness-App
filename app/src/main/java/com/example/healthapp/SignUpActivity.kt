package com.example.healthapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private var email: EditText? = null
    private var password: EditText? = null
    private var signUpButton: Button? = null
    private var username : EditText? = null
    private var userInfo = HashMap<String, String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        signUpButton = findViewById(R.id.sign_up)
        username = findViewById(R.id.username)

        signUpButton!!.setOnClickListener {
            signUpNewUser()
        }
    }

    private fun signUpNewUser() {
//        progressBar!!.visibility = View.VISIBLE

        val username: String = username!!.text.toString()
        val email: String = email!!.text.toString()
        val password: String = password!!.text.toString()

        UserInfo.setUsername(username)
        userInfo[email] = password
        UserInfo.setUserData(userInfo)

        // Verifies that username is valid
        if (!verifiedUsername(username)) {
            Toast.makeText(applicationContext, "Please enter a valid username (at least 3 characters)", Toast.LENGTH_LONG)
                .show()
            return
        }

        // Verifies that email & password are valid
        if (!verifiedEmail(email)) {
            Toast.makeText(applicationContext, "Please enter a valid email", Toast.LENGTH_LONG)
                .show()
//            progressBar?.visibility = View.INVISIBLE
            return
        }

        if (!verifiedPassword(password)) {
            Toast.makeText(
                applicationContext,
                "Please enter a valid password (at least 6 characters with 1 letter and 1 " +
                        "number", Toast.LENGTH_LONG
            ).show()
//            progressBar?.visibility = View.INVISIBLE
            return
        }

        if (userInfo.containsKey(email)) {
            Toast.makeText(applicationContext, "Sign up successful!", Toast.LENGTH_LONG)
                .show()
            val intent = Intent(this@SignUpActivity, RecordActivity::class.java)
            startActivity(intent)
        } else {
            Toast.makeText(
                applicationContext,
                "Sign up failed! Please try again later.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    private fun verifiedUsername(username: String?): Boolean {
        return if (username.isNullOrEmpty()) {
            false
        } else {
            USER_REGEX!!.matches(username)
        }
    }

    private fun verifiedEmail(email: String?): Boolean {
        return if (email.isNullOrEmpty()) {
            false
        } else {
            EMAIL_REGEX!!.matches(email)
        }
    }

    private fun verifiedPassword(password: String?): Boolean {
        return if (password.isNullOrEmpty()) {
            false
        } else {
            PASSWORD_REGEX!!.matches(password)
        }
    }

    public override fun onSaveInstanceState(savedInstanceState: Bundle){
        super.onSaveInstanceState(savedInstanceState)

        savedInstanceState.putSerializable("User Data", userInfo)
    }

    private fun restore(savedInstanceState: Bundle){
        if (savedInstanceState != null) {
            userInfo = savedInstanceState.getSerializable ("User Data") as HashMap<String, String>
        }
    }

    companion object {
        var USER_REGEX: Regex? = Regex("^[A-za-z0-9_-]{3,15}$")
        var EMAIL_REGEX: Regex? = Regex(
            "(?:[a-z0-9!#\$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#\$%&'" +
                    "*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x" +
                    "5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z" +
                    "0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4" +
                    "][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z" +
                    "0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|" +
                    "\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)])"
        )
        var PASSWORD_REGEX: Regex? = Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}\$")
    }
}
