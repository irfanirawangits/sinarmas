package com.sinarmas.gits

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory

import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.maps.DirectionsApi
import com.google.maps.GeoApiContext

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

        fromPoint = intent.getStringExtra("fromPoint")
        toPoint = intent.getStringExtra("toPoint")

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
