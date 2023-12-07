import { Module } from '@nestjs/common';
import { WeatherController } from './Weather.controller';
import { WeatherService } from './Weather.service';

@Module({
  imports: [],
  controllers: [WeatherController],
  providers: [WeatherService],
})
export class AppModule {}
