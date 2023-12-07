import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import { WeatherService } from './weather.service';
import {Weather} from "./Weather";

@Controller()
export class WeatherController {
  constructor(private readonly weatherService: WeatherService,) {}

  @Get()
  getAllWeather(): Weather[] {
    return this.weatherService.getAllWeather();
  }
  @Get(':city')
  getWeatherByCity(@Param('city') city: string): Weather{
    return this.weatherService.getCityWeather(city);
  }
  @Post()
  createWeather(@Body() wCreated: Weather): Weather{
    this.weatherService.addWeather(wCreated);
    return this.weatherService.getCityWeather(wCreated.city);
  }

  @Put(':name')
  setFavoriteCity(@Param('city') city: string, @Body() fav: boolean) : Weather{
    this.weatherService.setCityFavorite(city, fav);
    return this.weatherService.getCityWeather(city);
  }

  @Delete(':city')
  deleteWeather(@Param('city') city: string): void {
    return this.weatherService.removeWeather(city);
  }
}