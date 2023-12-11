package com.example.chevalier_dutra_weather_app_project

import java.io.Serializable

data class Position(
    val lon: Double,
    val lat: Double
): Serializable

data class Weather (
    val pos: Position,
    //val pos: LatLng,
    val city: String,
    val date: String,
    val time: String,
    val temperature: Double,
    val humidity: Double,
    val favorite: Boolean
): Serializable

