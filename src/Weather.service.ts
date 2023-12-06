import { Injectable } from '@nestjs/common';
import { Weather } from './Weather';
import { HttpService } from '@nestjs/axios/dist'
import {ApiWeather} from "./ApiWeather";
import {map, tap} from "rxjs";

@Injectable()
export class WeatherService {
  private dataWeather: Weather[] = [];

  constructor(private readonly httpService: HttpService) {} // Constructor
  async onModuleInit() : Promise<void>{
    await this.chargeDataFromAPI();
    await Promise.all([this.chargeDataFromAPI()]);
  }

  async chargeDataFromAPI(): Promise<any>{
    this.httpService
        .get<ApiWeather[]>('/api/explore/v2.1/catalog/datasets/arome-0025-enriched@public/records?limit=20')
        .pipe(
            map((resp)=>resp.data),
            tap((ApiWeather)=>{
              ApiWeather.forEach(e => {
                return this.dataWeather.push({
                  lat: e.pos.lat,
                  long: e.pos.long,
                  city: e.commune,
                  date: e.date.split("T")[0],
                  time: e.date.split("T")[1],
                  temperature: e.temp,
                  humidity: e.humidity,
                  favorite: false,
                });
              });
            }),
        )
        .subscribe();
  }
  addWeather(weather: Weather): void{
    if (!this.dataWeather.some((dataWeather)=>weather.city === dataWeather.city)){
      this.dataWeather.push(weather)
    }
  }
  getCityWeather(city: string): Weather {
    const w = this.dataWeather.find((weather)=>weather.city === city);
    if (!w){
      throw new Error(`No city with name ${city}`)
    }
    return w;
  }

  setCityFavorite(city: string, fav: boolean){
    const w = this.dataWeather.find((w)=> w.city === city);
    this.dataWeather[w.city].favorite = fav;
  }

  getAllWeather(): Weather[]{
    return this.dataWeather.sort((w1, w2) =>
        w1.city.toLowerCase().localeCompare(w2.city.toLowerCase()),
    );
  }

  getTotalNumberOfCities(): number{
    return this.dataWeather.length;
  }

  removeWeather(city: string){
    this.dataWeather = this.dataWeather.filter((w) => w.city !== city);
  }
}