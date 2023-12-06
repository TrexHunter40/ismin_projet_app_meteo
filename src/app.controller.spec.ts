import { Test, TestingModule } from '@nestjs/testing';
import { WeatherController } from './Weather.controller';
import { WeatherService } from './Weather.service';

describe('AppController', () => {
  let appController: WeatherController;

  beforeEach(async () => {
    const app: TestingModule = await Test.createTestingModule({
      controllers: [WeatherController],
      providers: [WeatherService],
    }).compile();

    appController = app.get<WeatherController>(WeatherController);
  });

  describe('root', () => {
    it('should return "Hello World!"', () => {
      expect(appController.getHello()).toBe('Hello World!');
    });
  });
});
