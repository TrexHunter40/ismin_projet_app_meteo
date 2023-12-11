# **Web & Android Development Project - Android App**
# Weather App

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
  - `Get `  provide all weathers
  - ` Post - createWeather` Adding a new weather to the API
  - ` Get - WeatherByCity` Return the weather according to the city name
  - ` Put - setFavoriteCity` adding a city as a favorite
  - ` Delete - DeleteWeather` Delete the weather for a city





## Installation

To install and run the ToiletMergency app:

1. Clone the repository.
2. Open the project in Android Studio.
3. Connect an Android device or use an emulator.
4. Build and run the project.

## Dependencies

- Retrofit: Used for making HTTP requests to the REST API server.
- Gson: Library for JSON serialization and deserialization.
