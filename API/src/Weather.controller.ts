import {Body, Controller, Delete, Get, Param, Post, Put} from '@nestjs/common';
import { WeatherService } from './Weather.service';
import { WeatherDto } from './WeatherDto'
import {Weather} from "./Weather";

@Controller()
export class WeatherController {
  constructor(private readonly weatherService: WeatherService,) {}

  @Get()
  getAllWeather(): Weather[] {
    return this.weatherService.getAllWeather();
  }

  @Get('/total') // Adicionado o método getTotalNumberOfCities ao controlador
  getTotalNumberOfCities(): number {
    console.log("Entrou no /total");
    return this.weatherService.getTotalNumberOfCities();
  }

  @Get(':city')
  getWeatherByCity(@Param('city') city: string): Weather{
    return this.weatherService.getCityWeather(city);
  }

  @Post('/weathers')
  createWeather(@Body() wCreated: Weather): Weather{
    this.weatherService.addWeather(wCreated);
    return this.weatherService.getCityWeather(wCreated.city);
  }

  @Put(':city') // Change the parameter name to 'city'
  setFavoriteCity(@Param('city') city: string, @Body() fav: boolean): Weather {
    this.weatherService.setCityFavorite(city, fav);
    return this.weatherService.getCityWeather(city);
  }

  @Delete('/weathers/:city') // Corrigido o path da rota
  deleteWeather(@Param('city') city: string): void {
    this.weatherService.removeWeather(city);
  }

}