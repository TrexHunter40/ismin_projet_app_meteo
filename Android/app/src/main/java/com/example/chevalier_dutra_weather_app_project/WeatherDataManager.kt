package com.example.chevalier_dutra_weather_app_project

class WeatherDataManager {

    private val data = HashMap<String, Weather>()
    fun addWeather(weather: Weather) {
        data[weather.city] = weather
    }

    fun getAllWeatherData(): ArrayList<Weather> {
        return  ArrayList(data.values
            .sortedBy { weather -> weather.city })
    }
}