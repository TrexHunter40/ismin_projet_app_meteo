package com.example.chevalier_dutra_weather_app_project

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import java.text.DecimalFormat

class WeatherAdapter (private var weatherData: List<Weather>, private val weatherListener: WeatherListener) : RecyclerView.Adapter<WeatherViewHolder>(){

    interface WeatherListener {
        fun onCityClicked(weather: Weather)
        fun onFavoriteClicked(weather: Weather)
    }

    private var favorite = false

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

        holder.image.setImageResource(getThermometerImage(weather.temperature))


        val drawableResource = if (weather.favorite) {
            android.R.drawable.btn_star_big_on
        } else {
            android.R.drawable.btn_star_big_off
        }
        holder.favorite_button.setBackgroundResource(drawableResource)


        holder.linkWeather(weather)
        holder.itemView.setOnClickListener {
            weatherListener.onCityClicked(weather)
        }

        holder.favorite_button.setOnClickListener {
            weatherListener.onFavoriteClicked(weather)
            //favorite = !favorite

        }
    }

    fun roundToFirstDigit(value: Double): Double {
        val decimalFormat = DecimalFormat("#.#")
        return decimalFormat.format(value).toDouble()
    }

    fun roundToFirstDigit(value: String): Double {
        try {
            val parsedValue = value.replace(",", ".").toDouble()
            val decimalFormat = DecimalFormat("#.#")
            return decimalFormat.format(parsedValue).toDouble()
        } catch (e: NumberFormatException) {
            Log.e("WeatherAdapter", "Error parsing value: $value")
            return (-300).toDouble()
        }
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
