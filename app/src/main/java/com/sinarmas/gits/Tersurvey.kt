package com.sinarmas.gits

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sinarmas.gits.adapter.CustomListSurvey
import kotlinx.android.synthetic.main.activity_tersurvey.*

class Tersurvey : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tersurvey)

        back.setOnClickListener {
            finish()
        }

        if (Tugas.dummyTokoTerpilih.isNotEmpty()) {
            listTersurvey.adapter = CustomListSurvey(this, Tugas.dummyTokoTerpilih)
            listTersurvey.setOnItemClickListener { parent, view, position, id ->

            }
        }

    }
}
