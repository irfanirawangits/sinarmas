package com.sinarmas.gits

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        back.setOnClickListener {
            finish()
        }

        pengaturan.setOnClickListener {
            startActivity(Intent(this, Pengaturan::class.java))
        }

    }
}
