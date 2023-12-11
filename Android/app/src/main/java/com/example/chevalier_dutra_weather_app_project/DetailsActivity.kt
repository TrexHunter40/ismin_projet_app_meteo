package com.example.chevalier_dutra_weather_app_project

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val ARG_WEATHER = "weather"

class DetailsActivity : AppCompatActivity() {


    private var weather: Weather? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title: TextView =  findViewById(R.id.a_details_city)
        val date: TextView =  findViewById(R.id.a_details_date)
        val time: TextView =  findViewById(R.id.a_details_time)
        val temperature: TextView =  findViewById(R.id.a_details_temperature)
        val humidity: TextView =  findViewById(R.id.a_details_humidity)
        val wind: TextView =  findViewById(R.id.a_details_wind)

        if (intent.hasExtra("weather")) {
            weather = intent.getSerializableExtra("weather") as? Weather

            weather?.let {
                title.text = it.city
                date.text = "Date: " + it.date
                time.text = "Time: " + it.time
                temperature.text = "Temperature: " + it.temperature
                humidity.text = "Humidity: " + it.humidity
                //wind.text = "Wind speed: " + it.wind

            }
        }
        else
            Log.e("DetailsActivity", "No weather passed to activity.")
    }
}