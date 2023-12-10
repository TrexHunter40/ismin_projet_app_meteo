# **Web & Android Development Project - API**
# Weather App

#### This project is deployed on Clever Cloud in 'ISMIN CS' - 'Project Weather'
## This project contains the following files

- A `Weather` interface containing 7 attributes:
    - `pos`: {number, number};
    - `city`: string;
    - `date`: string;
    - `time`: string;
    - `temperature`: number;
    - `humidity`: number;
    - `favorite`: boolean;


- A `ApiWeather` interface containing a replica of the data structure of the database for extraction:
  - `forecast`: string;
  - `position`: {number, number};
  - `timestamp`: string;
  - `"2_metre_temperature"`: number;
  - `minimum_temperature_at_2_metres`: number;
  - `maximum_temperature_at_2_metres`: number;
  - `"2_metre_relative_humidity"`: number;
  - `total_precipitation`: number;
  - `"10m_wind_speed"`: number;
  - `surface_net_solar_radiation`: number;
  - `surface_net_thermal_radiation`: number;
  - `surface_solar_radiation_downwards`: number;
  - `surface_latent_heat_flux`: number;
  - `surface_sensible_heat_flux`: number;
  - `commune`: string;
  - `code_commune`: string;

A `main` contains the application bootstrap with:
- The module to start
- The port that the server will listen : port 8080

A `Weather.controller`, which receives and processes requests
- `Get `  provide all weathers
- ` Post - createWeather` Adding a new weather to the API
- ` Get - WeatherByCity` Return the weather according to the city name
- ` Put - setFavoriteCity` adding a city as a favorite
- ` Delete - DeleteWeather` Delete the weather for a city

A `Weather.service` class containing
-` async chargeDataFromAPI()` Loads all weathers from the API
- `addWeather(weather: Weather)` Adds a city to the database
- `getCityWeather(city: string)` returning a `Weather`
- `getAllWeather()` returning an array of `Weather`
- `getTotalNumberOfWeather()` returning a number
- `setCityFavorite()` Changes the favorite status for a city
- `removeWeather(name: string)` Removes a city from the database

A `Weather.module` Links all Nest components

A `WeatherDto` classes with the weather constructor

##  Getting Started

Open a terminal, go to the directory and run the following commands:

```sh
# This will install all needed dependencies
npm install

# This will build the source and put the transpiled code in `/dist` directory
npm run build


# This will start the API 
npm run start:dev
```
