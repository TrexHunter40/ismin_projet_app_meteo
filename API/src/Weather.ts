import {Position} from "./Position";

export interface Weather {
    pos: Position;
    city: string;
    date: string;
    time: string;
    temperature: number;
    humidity: number;
    favorite: boolean;
}