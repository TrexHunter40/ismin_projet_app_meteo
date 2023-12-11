package com.example.chevalier_dutra_weather_app_project

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class WeatherAdapter (private var weatherData: List<Weather>, private val weatherListener: WeatherListener) : RecyclerView.Adapter<WeatherViewHolder>(){

    interface WeatherListener {
        fun onCityClicked(weather: Weather)
    }



    //var onRowClickListener: ((position: Int) -> Unit)? = null

    /*
    interface WeatherListener {
        fun onCityClicked(position: Int)
    }

     */

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
        holder.temperature.text = "" + roundToFirstDigit(weather.temperature) + "°C"
        holder.humidity.text = "" + roundToFirstDigit(weather.humidity) + "%"

        //val intTemperature = weather.temperature.substringBefore("°C").toInt()
        holder.image.setImageResource(getThermometerImage(weather.temperature))

        holder.linkWeather(weather)
        holder.itemView.setOnClickListener {
            weatherListener.onCityClicked(weather)
        }
        /*
        holder.itemView.setOnClickListener {
            onRowClickListener.onRowClick(position)
        }
        */
        /*
        holder.itemView.setOnClickListener {
            onRowClickListener?.invoke(position)
        }

         */

        /*
        holder.txvIsbn.text = "ISBN: ${book.isbn}"
        holder.txvTitle.text = book.title
        holder.txvAuthor.text = book.author
        holder.txvDate.text = book.date
        */
    }

    fun roundToFirstDigit(value: Double): Double {
        val decimalFormat = DecimalFormat("#.#")
        return decimalFormat.format(value).toDouble()
    }

    override fun getItemCount(): Int {
        return weatherData.size
    }


    fun getThermometerImage(temperature: Double): Int {
        return when {
            temperature < 10 -> R.drawable.thermometer_cold
            temperature < 25 -> R.drawable.thermometer_medium
            else -> R.drawable.thermometer_warm
        }
    }

    fun updateWeatherData(allWeatherData: List<Weather>) {
        weatherData = allWeatherData
        notifyDataSetChanged()
    }


}
