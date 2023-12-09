package com.example.chevalier_dutra_weather_app_project


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewmodel.CreationExtras.Empty.map
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


private const val ARG_WEATHERDATA = "param1"
class GoogleMapFragment : Fragment() {

    private lateinit var googleMap: GoogleMap
    private var weatherData: ArrayList<Weather> = arrayListOf()
    //private lateinit var clusterManager: ClusterManager<MyClusterItem> // Change MyClusterItem to your actual cluster item class
    private lateinit var clusterManager: ClusterManager<MyItem>

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */

        this.googleMap = googleMap

        Log.d("GoogleMapFragment", "Weather data: $weatherData")
        createMarkers(weatherData)

        if (weatherData.isNotEmpty()) {
            val france = LatLng(46.632524, 1.7)
            googleMap.moveCamera(CameraUpdateFactory.zoomTo(5.5f))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(france))
        } else {
            Log.e("GoogleMapFragment", "weatherData is empty")
        }

        //googleMap.setOnMarkerClickListener(onMarkerClick)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weatherData = it.getSerializable(ARG_WEATHERDATA) as ArrayList<Weather>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_google_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)
    }


    companion object {
        @JvmStatic
        fun newInstance(weatherData: ArrayList<Weather>) =
            GoogleMapFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_WEATHERDATA, weatherData)
                }
            }
    }


    fun createMarkers(dataList : ArrayList<Weather>) {
        dataList.forEach { weatherData ->
            val location = LatLng(weatherData.latitude.toDouble(), weatherData.longitude.toDouble())
            val markerOptions = MarkerOptions()
                .position(location)
                .title(weatherData.city)
                .snippet("Date: ${weatherData.date}\nTemperature: ${weatherData.temperature}\nHumidity: ${weatherData.humidity}")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow))
                //.icon(BitmapDescriptorFactory.fromBitmap())
                .alpha(0.8f)
            googleMap.addMarker(markerOptions)
        }
    }

    private fun setUpClusterer() {
        // Position the map.
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(51.503186, -0.126446), 10f))

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        clusterManager = ClusterManager(context, googleMap)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        googleMap.setOnCameraIdleListener(clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)

        // Add cluster items (markers) to the cluster manager.
        addItems()
    }

    /*
    fun onMarkerClick(marker: Marker): Boolean {

        // Retrieve the data from the marker.
        var clickCount = marker.tag as Int?

        // Check if a click count was set, then display the click count.
        if (clickCount != null) {
            clickCount = clickCount + 1
            marker.tag = clickCount
            Toast.makeText(
                this,
                marker.title +
                        " has been clicked " + clickCount + " times.",
                Toast.LENGTH_SHORT
            ).show()
        }

        // Return false to indicate that we have not consumed the event and that we wish
        // for the default behavior to occur (which is for the camera to move such that the
        // marker is centered and for the marker's info window to open, if it has one).
        return false
    }
    */
    inner class MarkerItem(
        lat: Double,
        lng: Double,
        title: String,
        snippet: String
    ) : ClusterItem {

        private val position: LatLng
        private val title: String
        private val snippet: String

        override fun getPosition(): LatLng {
            return position
        }

        override fun getTitle(): String {
            return title
        }

        override fun getSnippet(): String {
            return snippet
        }

        override fun getZIndex(): Float {
            return 0f
        }

        init {
            position = LatLng(lat, lng)
            this.title = title
            this.snippet = snippet
        }
    }
}

