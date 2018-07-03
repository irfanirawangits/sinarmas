package com.sinarmas.gits

import android.net.Uri
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_tutorial_video.*
import kotlinx.android.synthetic.main.activity_tutorial_video.view.*

class TutorialVideo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorial_video)

        videoPlayer.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.tes_video))
        videoPlayer.setOnCompletionListener {
            play.visibility = View.VISIBLE
        }
        play.setOnClickListener {
            play.visibility = View.GONE
            videoPlayer.start()
        }

    }
}
