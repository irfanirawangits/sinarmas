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

class CustomListSurvey (private val context: Context, private val toko: Array<String>) : BaseAdapter() {

    @SuppressLint("ViewHolder")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view : View = LayoutInflater.from(context).inflate(R.layout.custom_list_survey, parent, false)

        val namaToko = view.findViewById(R.id.textSurveyToko) as TextView
        namaToko.text = context.getString(R.string.survey_ke_string, toko[position].split(",")[0])
        val alamatToko = view.findViewById(R.id.textAlamatToko) as TextView
        alamatToko.text = "Jl. Menuju Surga No. 13 Bandung"

        return view
    }

    override fun getItem(position: Int): Any {
        return toko[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return toko.size
    }

}