import { NestFactory } from '@nestjs/core';
import { WeatherModule } from './Weather.module';
import {ValidationPipe} from "@nestjs/common";

// Listening to the port 8080
const port = process.env.PORT || 8080;
async function bootstrap() {
  const app = await NestFactory.create(WeatherModule);
  app.useGlobalPipes(new ValidationPipe())
  await app.listen(port);
}
bootstrap();
