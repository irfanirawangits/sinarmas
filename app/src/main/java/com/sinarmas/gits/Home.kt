package com.sinarmas.gits

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.design.widget.TabLayout
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.TabHost
import android.widget.Toast
import com.sinarmas.gits.adapter.CustomListSidebar
import com.sinarmas.gits.adapter.HomePagerAdapter
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
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
                }

            })
            homePager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(homeTab))
        } else{
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION),
                    RECORD_REQUEST_CODE)
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
