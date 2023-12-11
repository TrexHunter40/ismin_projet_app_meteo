package com.example.chevalier_dutra_weather_app_project

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface WeatherService {
    @GET("weatherData") // Method to get all available weathers
    fun getAllWeather(): Call<List<Weather>>

    @GET("total") // Method to get the total number of cities
    fun getTotalNumberOfCities(): Call<Int>

    @GET("weatherData/{city}") // Method to get the weather for a specific city
    fun getWeatherByCity(@Path("city") city: String): Call<Weather>

    @POST("weathers") // Method to create a weather data entry for a city
    fun createWeather(@Body wCreated: Weather): Call<Weather>


    @PUT("weatherData/{city}") // Method to set city as a favorite / remove it (given boolean)
    fun setFavoriteCity(@Path("city") city: String, @Body favorite: Boolean): Call<Weather>



    /*
    @PUT("weatherData/{city}")
    fun setFavoriteCity(@Path("city") city: String, @Body fav: Boolean): Call<Weather>

     */

    @DELETE("weathers/{city}") // Method to delete a city
    fun deleteWeather(@Path("city") city: String): Call<Void>
}