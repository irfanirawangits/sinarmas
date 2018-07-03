package com.sinarmas.gits

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sinarmas.gits.popup.PopUpLupaKataSandi
import kotlinx.android.synthetic.main.activity_lupa_kata_sandi.*

class LupaKataSandi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lupa_kata_sandi)

        back.setOnClickListener {
            finish()
        }

        ubahKataSandi.setOnClickListener {
            val popUpIntent = Intent(this, PopUpLupaKataSandi::class.java)
            startActivity(popUpIntent)
        }

    }
}
