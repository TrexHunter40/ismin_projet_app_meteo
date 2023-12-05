package com.example.chevalier_dutra_weather_app_project

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

private const val ARG_WEATHERDATA = "param1"
class GoogleMapFragment : Fragment() {
//class GoogleMapFragment constructor(weatherDataManager: WeatherDataManager) : Fragment() {

    private lateinit var googleMap: GoogleMap
    private var weatherData: ArrayList<Weather> = arrayListOf()



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
            val firstWeatherData = weatherData[0]
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLng(
                    LatLng(
                        firstWeatherData.latitude,
                        firstWeatherData.longitude
                    )
                )
            )
        } else {
            Log.e("GoogleMapFragment", "weatherData is empty")
        }
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
            googleMap.addMarker(markerOptions)
        }
    }
}