package com.sinarmas.gits.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.sinarmas.gits.Insentif
import com.sinarmas.gits.Tugas

class HomePagerAdapter(fm: FragmentManager, private var tabCount: Int) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        return when (position) {
            0 -> Tugas()
            1 -> Insentif()
            else -> null
        }
    }

    override fun getCount(): Int {
        return tabCount
    }

}