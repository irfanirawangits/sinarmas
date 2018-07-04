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

class CustomListSidebar (private val context: Context) : BaseAdapter() {

    private val iconId = listOf(R.drawable.reward_icon,R.drawable.history_icon ,
            R.drawable.insentif_icon, R.drawable.notification_icon, R.drawable.tutorials_icon)

    private val iconTextString = listOf("Reward", "History", "Insentif", "Notification", "Tutorials")

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.custom_list_sidebar, parent, false)

        val iconImage = view.findViewById(R.id.iconSidebar) as ImageView
        iconImage.setImageResource(iconId[position])
        val iconText = view.findViewById(R.id.textSidebar) as TextView
        iconText.text = iconTextString[position]

        return view
    }

    override fun getItem(position: Int): Any {
        return iconId[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return iconId.size
    }

}