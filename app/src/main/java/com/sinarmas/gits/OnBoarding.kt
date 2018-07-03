package com.sinarmas.gits

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.sinarmas.gits.adapter.CustomPagerAdapter
import kotlinx.android.synthetic.main.activity_on_boarding.*
import kotlinx.android.synthetic.main.custom_dot_indicator.*

class OnBoarding : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding)

        val tabView: View = LayoutInflater.from(this).inflate(R.layout.custom_dot_indicator, tab, false)
        tab.addView(tabView)

        val smartTab: SmartTabLayout = viewPagertab
        val pager: ViewPager = viewPager
        val imageList: ArrayList<Int> = ArrayList()
        imageList.add(R.drawable.tes_image)
        imageList.add(R.drawable.tes_image)
        imageList.add(R.drawable.tes_image)
        imageList.add(R.drawable.tes_image)
        pager.adapter = CustomPagerAdapter(this, imageList)
        smartTab.setViewPager(pager)

    }
}
