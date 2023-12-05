package com.example.chevalier_dutra_weather_app_project

import android.util.Log


class WeatherDataManager {

    private val data = HashMap<String, Weather>()
    fun addWeather(weather: Weather) {
        data[weather.city] = weather
        Log.d("WeatherDataManager", "Adding data: $weather ; new dataHashMap: $data")
    }

    fun getAllWeatherData(): ArrayList<Weather> {
        Log.d("WeatherDataManager", "getAllWeatherData() called")
        val res = ArrayList(data.values.sortedBy { weather -> weather.city })
        Log.d("WeatherDataManager", "data: $res")
        return  res
    }
}