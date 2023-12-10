package com.example.chevalier_dutra_weather_app_project

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface WeatherService {
    @GET("weatherData")
    fun getAllWeatherData(): Call<List<Weather>>
    @POST("weatherData")
    fun createWeatherData(@Body weather : Weather): Call<Weather>
}