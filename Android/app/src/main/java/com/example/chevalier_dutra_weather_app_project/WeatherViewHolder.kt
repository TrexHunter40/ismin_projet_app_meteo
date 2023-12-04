package com.example.chevalier_dutra_weather_app_project


import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherViewHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
    var city = rootView.findViewById<TextView>(R.id.row_weather_city)
    var date = rootView.findViewById<TextView>(R.id.row_weather_date)
    var time = rootView.findViewById<TextView>(R.id.row_weather_time)
    var temperature = rootView.findViewById<TextView>(R.id.row_weather_temperature)
    var humidity = rootView.findViewById<TextView>(R.id.row_weather_humidity)
}
