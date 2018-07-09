package com.sinarmas.gits

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext
import com.sinarmas.gits.adapter.CustomListDetailRoute
import kotlinx.android.synthetic.main.activity_detail_task.*
import kotlinx.android.synthetic.main.bottom_sheet_detail_task.*

class DetailTask : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var fromPoint: String
    private lateinit var toPoint: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_task)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        back.setOnClickListener {
            finish()
        }

        fromPoint = intent.getStringExtra("fromPoint")
        toPoint = intent.getStringExtra("toPoint")

        namaToko.text = toPoint.split(",")[0]
        alamatToko.text = toPoint.split(",")[1]
        collapsedView()
        val sheetBehavior = BottomSheetBehavior.from(bottomSheetDetailTask)
        sheetBehavior.isHideable = false
        sheetBehavior.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback(){

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                fabLocation.visibility = View.GONE
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_EXPANDED -> {
                        expandedView()
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                        collapsedView()
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                        collapsedView()
                    }
                }
            }

        })

        buttonBottomSheet.setOnClickListener {
            if (sheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            } else{
                sheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }
        }

        mulaiSurvey.setOnClickListener {
            sheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
        }

        val route = arrayOf("1", "2", "3")
        val headerView = LayoutInflater.from(this).inflate(R.layout.header_list_detail_route, null, false)
        val footerView = LayoutInflater.from(this).inflate(R.layout.footer_list_detail_route, null, false)
        listRoute.addHeaderView(headerView)
        listRoute.addFooterView(footerView)
        listRoute.adapter = CustomListDetailRoute(this, route)

    }

    private fun collapsedView() {
        fabLocation.animate().scaleX(1f).scaleY(1f).setDuration(10).start()
        fabLocation.visibility = View.VISIBLE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buttonBottomSheet.setImageDrawable(getDrawable(R.drawable.bar_icon))
        } else {
            buttonBottomSheet.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.bar_icon))
        }
        listRoute.visibility = View.GONE
        arrived.visibility = View.GONE
        imageIcon.animate().scaleX(1f).scaleY(1f).setDuration(10).start()
        imageIcon.visibility = View.VISIBLE
        mulaiSurvey.animate().scaleX(1f).scaleY(1f).setDuration(10).start()
        mulaiSurvey.visibility = View.VISIBLE
    }

    private fun expandedView() {
        fabLocation.visibility = View.GONE
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            buttonBottomSheet.setImageDrawable(getDrawable(R.drawable.down_arrow))
        } else {
            buttonBottomSheet.setImageDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.down_arrow))
        }
        imageIcon.visibility = View.GONE
        mulaiSurvey.visibility = View.GONE
        listRoute.animate().scaleX(1f).scaleY(1f).setDuration(10).start()
        listRoute.visibility = View.VISIBLE
        arrived.animate().scaleX(1f).scaleY(1f).setDuration(10).start()
        arrived.visibility = View.VISIBLE
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val nama = fromPoint.split(",")[0]
        val latUser = fromPoint.split(",")[1].toDouble()
        val lngUser = fromPoint.split(",")[2].toDouble()
        fromPoint = latUser.toString() + "," + lngUser.toString()
        mMap.addMarker(MarkerOptions().position(LatLng(latUser,lngUser)).title(nama))

        val namaToko = toPoint.split(",")[0]
        val lat = toPoint.split(",")[1].toDouble()
        val lng = toPoint.split(",")[2].toDouble()
        toPoint = lat.toString() + "," + lng.toString()
        mMap.addMarker(MarkerOptions().position(LatLng(lat,lng)).title(namaToko))

        val paths : ArrayList<LatLng> = arrayListOf()
        val geoApiContext = GeoApiContext.Builder().apiKey(getString(R.string.google_maps_key)).build()
        val directionsApiRequest = DirectionsApi.getDirections(geoApiContext, fromPoint, toPoint)
        try {
            val directionResult = directionsApiRequest.await()
            if (directionResult != null && directionResult.routes.isNotEmpty()) {
                val directionsRoute = directionResult.routes[0]
                for (leg in directionsRoute.legs) {
                    if (leg.steps != null) {
                        for (step in leg.steps) {
                            if (step.steps != null) {
                                for (stp in step.steps) {
                                    val polyline = stp.polyline
                                    if (polyline != null) {
                                        for (path in polyline.decodePath()) {
                                            paths.add(LatLng(path.lat, path.lng))
                                        }
                                    }
                                }
                            } else{
                                val polyline = step.polyline
                                if (polyline != null) {
                                    for (path in polyline.decodePath()) {
                                        paths.add(LatLng(path.lat, path.lng))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (e : Exception) {
            e.printStackTrace()
        }

        if (paths.size > 0) {
            val lineColor : Int = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getColor(R.color.sinarmas_red)
            } else {
                ContextCompat.getColor(this, R.color.sinarmas_red)
            }
            val polylineOptions = PolylineOptions().addAll(paths).color(lineColor).width(5f)
            mMap.addPolyline(polylineOptions)
        }

        mMap.uiSettings.isZoomControlsEnabled = false
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(latUser, lngUser), 13f))

    }
}
