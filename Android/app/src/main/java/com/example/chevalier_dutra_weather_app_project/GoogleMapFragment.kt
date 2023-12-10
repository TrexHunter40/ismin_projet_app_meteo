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


    private fun createMarkers(dataList : ArrayList<Weather>) {
        dataList.forEach { weatherEntry ->

            val tag = weatherEntry
            val location = LatLng(weatherEntry.latitude, weatherEntry.longitude)
            val title = weatherEntry.city
            val snippet = "Date: ${weatherEntry.date}\nTemperature: ${weatherEntry.temperature}\nHumidity: ${weatherEntry.humidity}"
            val icon = BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
            val alpha = 0.8f
            /*
            val markerOptions = MarkerOptions()
                .position(location)
                .title(weatherData.city)
                .snippet("Date: ${weatherData.date}\nTemperature: ${weatherData.temperature}\nHumidity: ${weatherData.humidity}")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE))
                //.icon(BitmapDescriptorFactory.fromResource(R.drawable.arrow))
                //.icon(BitmapDescriptorFactory.fromBitmap())
                .alpha(0.8f)
             */
            val newMarker = MarkerItem(tag, location,title, snippet, icon, alpha)
            //googleMap.addMarker(markerOptions)
            clusterManager.addItem(newMarker)
        }
    }

    private fun setUpClusterer() {
        // Position the map.
        //googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(france, 5.5f))

        // Initialize the manager with the context and the map.
        // (Activity extends context, so we can pass 'this' in the constructor.)
        clusterManager = ClusterManager(context, googleMap)

        // Point the map's listeners at the listeners implemented by the cluster
        // manager.
        googleMap.setOnCameraIdleListener(clusterManager)
        googleMap.setOnMarkerClickListener(clusterManager)




        // Set a listener for when the info window of a marker is clicked
        /*
        googleMap.setOnInfoWindowClickListener { marker ->
            // Get the weather object associated with the marker
            val weather = marker.tag as Weather

            Log.d("GoogleMap", "InfoWindow clicked")

            // Start DetailsActivity
            startActivity(DetailsActivity.newInstance(requireContext(), weather))

        }

         */



        // Add cluster items (markers) to the cluster manager.
        //addMarkersToClusterer()
    }

    /*
    googleMap.setOnInfoWindowClickListener { marker ->
        // Get the weather object associated with the marker
        val weather = marker.tag as Weather

        // Start DetailsActivity
        startActivity(DetailsActivity.newInstance(requireContext(), weather))
    }
     */

    /*
    private fun addMarkersToClusterer() {

        // Set some lat/lng coordinates to start with.
        var lat = 51.5145160
        var lng = -0.1270060

        // Set the title and snippet strings.
        val title = "This is the title"
        val snippet = "and this is the snippet."

        // Create a cluster item for the marker and set the title and snippet using the constructor.
        val infoWindowItem = MarkerItem(lat, lng, title, snippet)
        // Add the cluster item (marker) to the cluster manager.
        clusterManager.addItem(infoWindowItem)


        // Add ten cluster items in close proximity, for purposes of this example.
        for (i in 0..9) {
            val offset = i / 60.0
            lat += offset
            lng += offset
            val offsetItem =
                MarkerItem(lat, lng, "Title $i", "Snippet $i")
            clusterManager.addItem(offsetItem)
        }
    }
    */

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

