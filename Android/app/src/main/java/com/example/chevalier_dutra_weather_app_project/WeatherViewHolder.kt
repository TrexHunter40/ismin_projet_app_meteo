package com.example.chevalier_dutra_weather_app_project


import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {


    private var weather: Weather? = null    //with this, each row "knows" which weather it's showing

    var image = rootView.findViewById<ImageView>(R.id.row_weather_image)

    var city = rootView.findViewById<TextView>(R.id.row_weather_city)
    var date = rootView.findViewById<TextView>(R.id.row_weather_date)
    var time = rootView.findViewById<TextView>(R.id.row_weather_time)
    var temperature = rootView.findViewById<TextView>(R.id.row_weather_temperature)
    var favorite_button = rootView.findViewById<Button>(R.id.row_weather_favorite_button)

    fun linkWeather(weather: Weather) {
        this.weather = weather
    }

    init {
        rootView.setOnClickListener {
            if (rootView.isAttachedToWindow) {
                weather?.let { weather ->
                    val intent = Intent(rootView.context, DetailsActivity::class.java)
                    intent.putExtra("weather", weather)
                    rootView.context.startActivity(intent)
                }
            }
            else
                Log.e("ViewHolder", "Details opened")
        }
    }

}
