package com.example.chevalier_dutra_weather_app_project

import java.io.Serializable

data class Weather (val city: String, val date: String, val time: String, val temperature: String, val humidity: String): Serializable