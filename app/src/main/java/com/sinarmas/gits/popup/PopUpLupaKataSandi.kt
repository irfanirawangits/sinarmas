package com.sinarmas.gits.popup

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sinarmas.gits.R
import kotlinx.android.synthetic.main.activity_pop_up_lupa_kata_sandi.*

class PopUpLupaKataSandi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up_lupa_kata_sandi)

        close.setOnClickListener {
            finish()
        }

        ok.setOnClickListener {
            finish()
        }

    }
}
