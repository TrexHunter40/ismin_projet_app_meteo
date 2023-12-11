import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import { WeatherService } from './Weather.service';
import { WeatherDto } from './WeatherDto'
import {Weather} from "./Weather";

@Controller('weatherData')
export class WeatherController {
  //constructor
  constructor(private readonly weatherService: WeatherService,) {}

  @Get() // Method to get all available weathers
  getAllWeather(): Weather[] {
    return this.weatherService.getAllWeather();
  }

  @Get('/total') // Method to get the total number of cities
  getTotalNumberOfCities(): number {
    console.log("Entrou no /total");
    return this.weatherService.getTotalNumberOfCities();
  }

  @Get(':city') // Method to get the weather for a specific city
  getWeatherByCity(@Param('city') city: string): Weather{
    return this.weatherService.getCityWeather(city);
  }

  @Post('/weathers') // Method to create the weather data for a city
  createWeather(@Body() wCreated: Weather): Weather{
    this.weatherService.addWeather(wCreated);
    return this.weatherService.getCityWeather(wCreated.city);
  }

  @Put(':city') // Method to set city as a favorite
  setFavoriteCity(@Param('city') city: string, @Body() fav: boolean): Weather {
    this.weatherService.setCityFavorite(city, fav);
    return this.weatherService.getCityWeather(city);
  }

  @Delete('/weathers/:city') // Method to delete a city
  deleteWeather(@Param('city') city: string): void {
    this.weatherService.removeWeather(city);
  }
//
}