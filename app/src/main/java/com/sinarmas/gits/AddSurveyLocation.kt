package com.sinarmas.gits

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_add_survey_location.*

class AddSurveyLocation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_survey_location)

        back.setOnClickListener {
            finish()
        }

        done.setOnClickListener {
            finish()
        }

    }
}
