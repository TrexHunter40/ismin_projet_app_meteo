# **Web & Android Development Project - Android App**
# Made by Carlos Dutra and Léo Chevalier
# CD Weather App

## Structure

- A `Weather` class containing 7 attributes:
    - `pos`: {number, number};
    - `city`: string;
    - `date`: string;
    - `time`: string;
    - `temperature`: number;
    - `humidity`: number;
    - `favorite`: boolean;

- A `MainActivity` that shows the following elements:
  - `Menu`: allows to switch between the fragments;
  - `ListFragment`: shows a list of the weather data entries;
  - `MapFragment`: shows the data entries on a map;
  - `AboutFragment`: shows some info about the app;
  - `Refresh button`: refreshes the data when clicked;


A `WeatherService`, which receives and processes requests
  - ` Get `  provide all weathers
  - ` Post - createWeather` Adding a new weather to the API
  - ` Get - WeatherByCity` Return the weather according to the city name
  - ` Put - setFavoriteCity` adding a city as a favorite
  - ` Delete - DeleteWeather` Delete the weather for a city


## Features

- Get weather data from opendata for every city in France 
  (although we can only import very few due to restrictions from opendata so it's more of a proof of concept)
- Check each city's weather in the list quickly 
- Get details about the weather in each city by clicking its entry in the list
- Add cities to your favorites so that you don't have to search for them (unstable)
- Refresh the data to always be up to date with the weather
- Also shows the cities which we have weather data about on GoogleMaps
- For a better user experience, the markers on the map 
  that are too close to each other get clustered up to increase visibility
- Get more information about the dataset used for this app in the About section (little (i) in the top right corner)
- Use return key to go back from the Details (drag your mouse from the very left to the very right of the screen on emulator)


## Installation

To install and run CD Weather App:

1. Clone the repository.
2. Open the Android directory in Android Studio.
3. Launch on an emulator or a physical device (unstable, due to French comma syntax it seems: "." converted to "," for some reason).
4. Build and run the project.
