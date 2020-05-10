package com.zafaris.twitterclone

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.parse.ParseUser
import kotlinx.android.synthetic.main.activity_login.*

private const val TAG = "LoginActivity"

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //title = "Login"

        if (ParseUser.getCurrentUser() == null) {
            Toast.makeText(this, "Not logged in", Toast.LENGTH_SHORT).show()
        } else {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        button_login_signup.setOnClickListener {
            buttonOnClick()
        }
    }

    private fun buttonOnClick() {
        if (edittext_username.editText?.text?.isEmpty()!!) {
            Toast.makeText(this, "Please enter a username", Toast.LENGTH_SHORT).show()
        } else {
            if (edittext_password.editText?.text?.isEmpty()!!) {
                Toast.makeText(this, "Please enter a password", Toast.LENGTH_SHORT).show()
            } else {
                loginOrSignup(
                    edittext_username.editText!!.text.toString(),
                    edittext_password.editText!!.text.toString()
                )
            }
        }
    }

    private fun loginOrSignup(username: String, password: String) {
        ParseUser.logInInBackground(username, password) { parseUser, _ ->
            if (parseUser != null) {
                Log.d(TAG, "parseUser != null")
                Log.d(TAG, "Successfully logged in")
                Toast.makeText(this, "Successfully logged in", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Log.d(TAG, "parseUser == null")
                Log.d(TAG, "Not signed up")

                val user = ParseUser()
                user.username = username
                user.setPassword(password)
                user.signUpInBackground {
                    Log.d(TAG, "Successfully signed up")
                    Toast.makeText(this, "Successfully signed up", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}
