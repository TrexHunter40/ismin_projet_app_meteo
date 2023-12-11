package com.example.chevalier_dutra_weather_app_project


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterItem
import com.google.maps.android.clustering.ClusterManager


private const val ARG_WEATHERDATA = "param1"
class GoogleMapFragment : Fragment() {

    private lateinit var googleMap: GoogleMap
    private var weatherData: ArrayList<Weather> = arrayListOf()

    private lateinit var clusterManager: ClusterManager<MarkerItem>

    private val france = LatLng(46.632524, 1.7)

    private val callback = OnMapReadyCallback { googleMap ->

        this.googleMap = googleMap
        setUpClusterer()

        Log.d("GoogleMapFragment", "Weather data: $weatherData")
        createMarkers(weatherData)

        if (weatherData.isNotEmpty()) {
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france, 5.5f))
        } else {
            Log.e("GoogleMapFragment", "weatherData is empty")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weatherData = it.getSerializable(ARG_WEATHERDATA) as ArrayList<Weather>
        }
        Log.d("GoogleMap", "Map data: $weatherData")
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


    private fun createMarkers(dataList : ArrayList<Weather>) {
        dataList.forEach { weatherEntry ->

            val tag = weatherEntry
            val location = LatLng(weatherEntry.pos.lat, weatherEntry.pos.lon)
            val title = weatherEntry.city
            val snippet = "Temperature: ${weatherEntry.temperature.toInt()}°C"
            val icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            val alpha = 0.8f

            val newMarker = MarkerItem(tag, location, title, snippet, icon, alpha)

            clusterManager.addItem(newMarker)
        }
    }

    private fun setUpClusterer() {
        clusterManager = ClusterManager(context, googleMap)

        googleMap.setOnCameraIdleListener(clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)

        /*
        googleMap.setOnInfoWindowClickListener { marker ->
            // Get the weather object associated with the marker
            val weather = marker.tag as Weather

            Log.d("GoogleMap", "InfoWindow clicked")

            // Start DetailsActivity
            Log.d("MainActivity", "City Clicked. Opening Details Activity.")
            val intent = Intent(activity, DetailsActivity::class.java)
            intent.putExtra("weather", weather)
            this.startActivity(intent)

        }

         */
    }

    inner class MarkerItem(
        tag: Weather,
        position: LatLng,
        title: String,
        snippet: String,
        icon: BitmapDescriptor,
        alpha: Float
    ) : ClusterItem {

        private val tag: Weather
        private val position: LatLng
        private val title: String
        private val snippet: String
        private val icon: BitmapDescriptor
        private val alpha: Float

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
            this.tag = tag
            this.position = position
            this.title = title
            this.snippet = snippet
            this.icon = icon
            this.alpha = alpha
        }
    }
}

