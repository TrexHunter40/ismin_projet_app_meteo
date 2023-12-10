import { Injectable } from '@nestjs/common';
import { Weather } from './Weather';
import { HttpService } from '@nestjs/axios/dist'
import {ApiWeather} from "./ApiWeather";
import {firstValueFrom, map, tap} from "rxjs";
import {WeatherDto} from "./WeatherDto";

@Injectable()
export class WeatherService {
  private dataWeather: Weather[] = [];

  constructor(private readonly httpService: HttpService) {} // Constructor
  async onModuleInit() : Promise<void>{ // Constructor
    await this.chargeDataFromAPI();
    //await Promise.all([this.chargeDataFromAPI()]);
    //console.log(this.dataWeather);
  }

  async chargeDataFromAPI(): Promise<void>{ // Loads all data from the API
    return firstValueFrom(
        this.httpService.get('https://data.opendatasoft.com/api/explore/v2.1/catalog/datasets/arome-0025-enriched@public/records?where=commune%20is%20not%20null&limit=100')
            .pipe(
                map((response) => response.data.results),
                tap((w1 : ApiWeather[]) => {
                  w1.forEach(w => {
                    this.addWeather({
                      pos: w.position,
                      city: w.commune,
                      date: w.forecast.split("T")[0],
                      time: w.forecast.split("T")[1],
                      temperature: w["2_metre_temperature"],
                      humidity: w["2_metre_relative_humidity"],
                      favorite: false,
                    });
                  });
                }),
                map(() => undefined),
            ),
    );
  }

  addWeather(weather: Weather): void{ // Add weather for a city
    if (!this.dataWeather.some((dataWeather)=>weather.city === dataWeather.city)){
      this.dataWeather.push(weather)
    }
  }

  getCityWeather(city: string): Weather | null { // Retrieve the weather to a city using its name
    const w = this.dataWeather.find((weather) => weather.city === city);
    return w || null;
  }

  setCityFavorite(city: string, fav: boolean) { // Change the favorite status of a city
    const weather = this.dataWeather.find((w) => w.city === city);
    if (weather) {
      weather.favorite = fav;
    } else {
      throw new Error(`No city with name ${city}`);
    }
  }

  getAllWeather(): Weather[]{ // Retrieve all the weathers
    return this.dataWeather.sort((w1, w2) =>
        w1.city.toLowerCase().localeCompare(w2.city.toLowerCase()),
    );
  }

  getTotalNumberOfCities(): number{ // Get total number of cities
    return this.dataWeather.length;
  }

  removeWeather(city: string){ // Delete a city from the database
    this.dataWeather = this.dataWeather.filter((w) => w.city !== city);
  }
}