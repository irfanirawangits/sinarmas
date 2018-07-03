package com.sinarmas.gits

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        signIn.setOnClickListener{
            val tutorialIntent = Intent(this, Tutorial::class.java)
            startActivity(tutorialIntent)
        }

        forgotPassword.setOnClickListener {
            val lupaSandiIntent = Intent(this, LupaKataSandi::class.java)
            startActivity(lupaSandiIntent)
        }

        signUp.setOnClickListener {
            val signUpIntent = Intent(this, Register::class.java)
            startActivity(signUpIntent)
        }

    }
}
