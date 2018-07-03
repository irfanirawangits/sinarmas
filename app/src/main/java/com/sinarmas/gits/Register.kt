package com.sinarmas.gits

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        back.setOnClickListener{
            finish()
        }

        register.setOnClickListener {
            val phoneVerifiIntent = Intent(this, PhoneVerification::class.java)
            startActivity(phoneVerifiIntent)
        }

        signIn.setOnClickListener{
            val signIntent = Intent(this, Login::class.java)
            startActivity(signIntent)
        }

    }
}
