import { IsNotEmpty, IsString, IsNumber } from "class-validator";

export class WeatherDto {
    @IsNotEmpty()
    @IsString()
    readonly lat: string;

    @IsNotEmpty()
    @IsString()
    readonly long: string;

    @IsNotEmpty()
    @IsString()
    readonly city: string;

    @IsNotEmpty()
    @IsString()
    readonly date: string;

    @IsNotEmpty()
    @IsString()
    readonly time: string;

    @IsNotEmpty()
    @IsString()
    readonly temperature: string;

    @IsNotEmpty()
    @IsString()
    readonly humidity: string;

    fav: boolean;

    constructor(lat: string, long: string, city: string, date: string,
                time: string, temperature: string, humidity: string) {
        this.lat = lat;
        this.long = long;
        this.city = city;
        this.date = date;
        this.time = time;
        this.temperature = temperature;
        this.humidity = humidity;
        this.fav = false;
    }
}

//lat: string;
//     long: string;
//     city: string;
//     date: string;
//     time: string;
//     temperature: string;
//     humidity: string;
//     favorite: boolean;