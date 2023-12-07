import { Module } from '@nestjs/common';
import { WeatherController } from './Weather.controller';
import { WeatherService } from './Weather.service';
import {HttpModule} from "@nestjs/axios";

@Module({
  imports: [HttpModule],
  controllers: [WeatherController],
  providers: [WeatherService],
})
export class WeatherModule {}
