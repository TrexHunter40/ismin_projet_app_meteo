export interface ApiWeather{
    date: string;
    pos: {lat: string, long: string };
    base: string;
    temp: string;
    maxTemp: string;
    minTemp: string;
    humidity: string;
    precipitation: string;
    windSpeed: string;
    solarRadiation: string;
    thermalRadiation: string;
    downwards: string;
    heatFlux: string;
    commune: string;
    codCommune: string;
}