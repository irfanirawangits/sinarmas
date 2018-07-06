package com.sinarmas.gits

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_pengaturan.*

class Pengaturan : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pengaturan)

        back.setOnClickListener {
            finish()
        }

        keluar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            finishAffinity()
        }

    }
}
