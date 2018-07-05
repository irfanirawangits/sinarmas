package com.sinarmas.gits

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Tugas : Fragment(), OnMapReadyCallback {

    private lateinit var gMap: GoogleMap

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tugas, container, false)

        mapView = view.findViewById(R.id.map) as MapView
        mapView.onCreate(savedInstanceState)
        mapView.onResume()

        try {
            MapsInitializer.initialize(activity!!.applicationContext)
        } catch (e : Exception) {
            e.printStackTrace()
        }
        mapView.getMapAsync(this)

        return view
    }

    override fun onMapReady(mMap: GoogleMap) {
        gMap = mMap

        gMap.uiSettings.isMyLocationButtonEnabled = false
//        gMap.isMyLocationEnabled = true

        val myPositionIcon = BitmapDescriptorFactory.fromBitmap(resizeBitmap(R.drawable.compas_icon, 50, 50))
        val storeIcon = BitmapDescriptorFactory.fromBitmap(resizeBitmap(R.drawable.red_ellipse, 50, 50))
        val selectedStoreIcon = BitmapDescriptorFactory.fromBitmap(resizeBitmap(R.drawable.green_ellipse, 50, 50))
        val markerOptions: ArrayList<MarkerOptions> = arrayListOf()
        //my position -6.9464697,107.6608973
        val nama = myPosition.split(",")[0]
        val lat = myPosition.split(",")[1].toDouble()
        val lng = myPosition.split(",")[2].toDouble()
        val latLng = LatLng(lat, lng)
        markerOptions.add(MarkerOptions().position(latLng).icon(myPositionIcon).title(nama))

        //store position
        for (ltlg in dummyToko) {
            val namaToko = ltlg.split(",")[0]
            val lat = ltlg.split(",")[1].toDouble()
            val lng = ltlg.split(",")[2].toDouble()
            markerOptions.add(MarkerOptions().position(LatLng(lat,lng)).icon(storeIcon).title(namaToko))
        }

        //selected store position
        for (ltlg in dummyTokoTerpilih) {
            val namaToko = ltlg.split(",")[0]
            val lat = ltlg.split(",")[1].toDouble()
            val lng = ltlg.split(",")[2].toDouble()
            markerOptions.add(MarkerOptions().position(LatLng(lat,lng)).icon(selectedStoreIcon).title(namaToko))
        }

        val cameraUpdate: CameraUpdate
        val builder = LatLngBounds.builder()
        for (markerOption in markerOptions) {
            builder.include(gMap.addMarker(markerOption).position)
        }
        cameraUpdate = CameraUpdateFactory.newLatLngBounds(builder.build(), 300, 300, 1)
        gMap.moveCamera(cameraUpdate)
        gMap.animateCamera(cameraUpdate)
    }

    private fun resizeBitmap(drawableId: Int, width: Int, height: Int) : Bitmap {
        val bitmap = BitmapFactory.decodeResource(resources, drawableId)
        return Bitmap.createScaledBitmap(bitmap, width, height, false)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                Tugas().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }

        lateinit var  mapView: MapView

        val myPosition = "Gits,-6.9464697,107.6608973"
        val dummyToko = arrayOf("Toko A,-6.9453061,107.6600015", "Toko B,-6.9455205,107.6601543",  "Toko D,-6.9464284,107.659575")
        val dummyTokoTerpilih = arrayOf("Toko C,-6.9458183,107.6570499,5 Poin")

    }
}
