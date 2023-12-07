import {Position} from "./Position";

export interface ApiWeather {
    forecast: string;
    position: Position;
    timestamp: string;
    "2_metre_temperature": number;
    minimum_temperature_at_2_metres: number;
    maximum_temperature_at_2_metres: number;
    "2_metre_relative_humidity": number;
    total_precipitation: number;
    "10m_wind_speed": number;
    surface_net_solar_radiation: number;
    surface_net_thermal_radiation: number;
    surface_solar_radiation_downwards: number;
    surface_latent_heat_flux: number;
    surface_sensible_heat_flux: number;
    commune: string;
    code_commune: string;
}
