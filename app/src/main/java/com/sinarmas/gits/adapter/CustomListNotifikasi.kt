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

class CustomListNotifikasi (private val context: Context, private val notif: Array<String>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.custom_list_notifikasi, parent, false)

        return view
    }

    override fun getItem(position: Int): Any {
        return notif[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return notif.size
    }

}