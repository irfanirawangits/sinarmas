package com.sinarmas.gits

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.sinarmas.gits.adapter.CustomListNotifikasi
import kotlinx.android.synthetic.main.activity_notifikasi.*

class Notifikasi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notifikasi)

        back.setOnClickListener {
            finish()
        }

        listNotifikasi.adapter = CustomListNotifikasi(this, arrayOf("1", "2", "3"))

    }
}
