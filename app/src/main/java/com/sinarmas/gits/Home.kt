package com.sinarmas.gits

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.*
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import com.sinarmas.gits.adapter.CustomListSidebar
import com.sinarmas.gits.adapter.CustomListSurvey
import com.sinarmas.gits.adapter.HomePagerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_home.*

class Home : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener,
        Tugas.OnFragmentInteractionListener, Insentif.OnFragmentInteractionListener {

    private val TAG = "HOME"
    private val RECORD_REQUEST_CODE = 101

    override fun onFragmentInteraction(uri: Uri) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        setSupportActionBar(toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        val customAdapter = CustomListSidebar(this)
        customSidebar.adapter = customAdapter

        nav_view.setNavigationItemSelectedListener(this)
        val sheetBehavior = BottomSheetBehavior.from(bottomSheet)
        sheetBehavior.isHideable = false
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                fabLocation.visibility = View.GONE
                fabSurvey.visibility = View.GONE
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        fabLocation.visibility = View.GONE
                        fabSurvey.visibility = View.GONE
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            buttonBottomSheet.setImageDrawable(getDrawable(R.drawable.down_arrow))
                        } else {
                            buttonBottomSheet.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.down_arrow))
                        }
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        fabLocation.animate().scaleX(1f).scaleY(1f).setDuration(0).start()
                        fabLocation.visibility = View.VISIBLE
                        fabSurvey.animate().scaleX(1f).scaleY(1f).setDuration(0).start()
                        fabSurvey.visibility = View.VISIBLE
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            buttonBottomSheet.setImageDrawable(getDrawable(R.drawable.bar_icon))
                        } else {
                            buttonBottomSheet.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.bar_icon))
                        }
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        fabLocation.animate().scaleX(1f).scaleY(1f).setDuration(0).start()
                        fabLocation.visibility = View.VISIBLE
                        fabSurvey.animate().scaleX(1f).scaleY(1f).setDuration(0).start()
                        fabSurvey.visibility = View.VISIBLE
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            buttonBottomSheet.setImageDrawable(getDrawable(R.drawable.bar_icon))
                        } else {
                            buttonBottomSheet.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.bar_icon))
                        }
                    }
                }
            }
        })

        homeTab.addTab(homeTab.newTab().setText(getString(R.string.tugas_text)))
        homeTab.addTab(homeTab.newTab().setText(getString(R.string.insentif_text)))

        if (checkPermissions()) {
            homePager.adapter = HomePagerAdapter(supportFragmentManager, homeTab.tabCount)
            homeTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(tab: TabLayout.Tab?) {

                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabSelected(tab: TabLayout.Tab) {
                    homePager.currentItem = tab.position
                    if (tab.text == getString(R.string.tugas_text)) {
                        bottomSheet.animate().scaleX(1f).scaleY(1f).setDuration(0).start()
                        bottomSheet.visibility = View.VISIBLE
                        sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                        fabLocation.animate().scaleX(1f).scaleY(1f).setDuration(0).start()
                        fabLocation.visibility = View.VISIBLE
                        fabSurvey.animate().scaleX(1f).scaleY(1f).setDuration(0).start()
                        fabSurvey.visibility = View.VISIBLE
                    } else {
                        bottomSheet.visibility = View.GONE
                        fabLocation.visibility = View.GONE
                        fabSurvey.visibility = View.GONE
                    }
                }

            })
            homePager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(homeTab))
        } else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION),
                    RECORD_REQUEST_CODE)
        }

        buttonBottomSheet.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    buttonBottomSheet.setImageDrawable(getDrawable(R.drawable.down_arrow))
                } else {
                    buttonBottomSheet.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.down_arrow))
                }
            } else{
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    buttonBottomSheet.setImageDrawable(getDrawable(R.drawable.bar_icon))
                } else {
                    buttonBottomSheet.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.bar_icon))
                }
            }
        }

        if (Tugas.dummyTokoTerpilih.isNotEmpty()) {
            totalTersurvey.setOnClickListener {
                val tersurveyIntent = Intent(this, Tersurvey::class.java)
                startActivity(tersurveyIntent)
            }
        }

        if (Tugas.dummyToko.isNotEmpty()) {
                tersediaUntukSurvey.text = getString(R.string.tersedia_untuk_string, Tugas.dummyToko.size.toString())
                listSurvey.adapter = CustomListSurvey(this, Tugas.dummyToko)
                listSurvey.setOnItemClickListener { parent, view, position, id ->
                    val detailTaskIntent = Intent(this, DetailTask::class.java)
                    detailTaskIntent.putExtra("fromPoint", Tugas.myPosition)
                    detailTaskIntent.putExtra("toPoint", Tugas.dummyToko[position])
                    startActivity(detailTaskIntent)
            }
        }

    }

    private fun checkPermissions() : Boolean {
        val permission1 = ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
        val permission2 = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        } else {
            ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION)
        }
        return permission1 != PackageManager.PERMISSION_GRANTED && permission2 != PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<String>, grantResults: IntArray) {
        when (requestCode) {
            RECORD_REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Log.i(TAG, "Permission has been denied by user")
                } else {
                    Log.i(TAG, "Permission has been granted by user")
                    homePager.adapter = HomePagerAdapter(supportFragmentManager, homeTab.tabCount)
                    homeTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                        override fun onTabReselected(tab: TabLayout.Tab?) {

                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {

                        }

                        override fun onTabSelected(tab: TabLayout.Tab) {
                            homePager.currentItem = tab.position
                        }

                    })
                    homePager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(homeTab))
                }
            }
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

}
