package com.sinarmas.gits.adapter

import android.app.Activity
import android.support.v4.view.PagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sinarmas.gits.R
import kotlinx.android.synthetic.main.image_container.view.*

/**
 * Created by Ryvaldie I.H on 02/07/18.
 * Void Group.
 */

class CustomPagerAdapter constructor(private val activity: Activity, private val imageList: ArrayList<Int>) : PagerAdapter() {

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView: View = LayoutInflater.from(activity).inflate(R.layout.image_container, container, false)
        container.addView(imageView)

        imageView.image.setImageResource(imageList[position])

        return imageView
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return if (imageList.isEmpty()) {
            0
        } else {
            imageList.size
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {

    }

}
