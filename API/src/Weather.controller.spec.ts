import { Test, TestingModule } from '@nestjs/testing';
import { WeatherController } from './Weather.controller';
import { WeatherService } from './Weather.service';

describe('WeatherController', () => {
  let appController: WeatherController;

  beforeEach(async () => {
    const app: TestingModule = await Test.createTestingModule({
      controllers: [WeatherController],
      providers: [WeatherService],
    }).compile();

    appController = app.get<WeatherController>(WeatherController);
  });
});
