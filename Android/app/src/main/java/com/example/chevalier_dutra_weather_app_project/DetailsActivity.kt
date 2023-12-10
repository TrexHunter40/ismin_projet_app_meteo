package com.example.chevalier_dutra_weather_app_project

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val ARG_WEATHER = "weather"

class DetailsActivity : AppCompatActivity() {

    private val title: TextView =  findViewById(R.id.city_name)
    private lateinit var weather: Weather

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        weather = intent.getSerializableExtra(ARG_WEATHER) as Weather

        setContentView(R.layout.activity_details)
        title.text = weather.city
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(context: Context, weather: Weather) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(ARG_WEATHER, weather)
            }
    }

}