package com.sinarmas.gits.adapter

import android.annotation.SuppressLint
import android.app.Activity
import android.app.RemoteInput
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.sinarmas.gits.R

class CustomGridMembershipAdapter (private val context: Context, private val voucher: Array<String>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.custom_grid_membership_info, parent, false)

        return view
    }

    override fun getItem(position: Int): Any {
        return voucher[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return voucher.size
    }

}