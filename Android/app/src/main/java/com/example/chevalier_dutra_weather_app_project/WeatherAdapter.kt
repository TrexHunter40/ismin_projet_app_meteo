package com.example.chevalier_dutra_weather_app_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter (private var weatherData: List<Weather>) : RecyclerView.Adapter<WeatherViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val rowView = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_weather, parent, false)
        return WeatherViewHolder(rowView)
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        val weather = weatherData[position]
        holder.city.text = weather.city
        holder.date.text = weather.date
        holder.time.text = weather.time
        holder.temperature.text = weather.temperature
        holder.humidity.text = weather.humidity

        /*
        holder.txvIsbn.text = "ISBN: ${book.isbn}"
        holder.txvTitle.text = book.title
        holder.txvAuthor.text = book.author
        holder.txvDate.text = book.date
        */
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }

    fun updateBooks(allWeatherData: List<Weather>) {
        weatherData = allWeatherData
        notifyDataSetChanged()
    }
}
