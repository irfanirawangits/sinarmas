package com.sinarmas.gits

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_tutorial.*

class Tutorial : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial)

        seeTutorial.setOnClickListener{
            val tutorialIntent = Intent(this, TutorialVideo::class.java)
            startActivity(tutorialIntent)
        }

    }
}
