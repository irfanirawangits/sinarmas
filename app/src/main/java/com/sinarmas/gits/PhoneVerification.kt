package com.sinarmas.gits

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_phone_verification.*

class PhoneVerification : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_verification)

        submit.setOnClickListener{
            val tutorialIntent = Intent(this, Tutorial::class.java)
            startActivity(tutorialIntent)
        }

    }
}
