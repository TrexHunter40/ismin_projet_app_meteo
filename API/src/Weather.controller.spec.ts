import { Test, TestingModule } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import * as request from 'supertest';
import { WeatherModule } from '../src/Weather.module';
import supertest from "supertest";

describe('WeatherAPI', () => {
  let app: INestApplication;
  let httpRequester: supertest.SuperTest<supertest.Test>;

  beforeEach(async () => {
    const moduleRef = await Test.createTestingModule({
      imports: [WeatherModule],
    }).compile();

    app = moduleRef.createNestApplication();
    await app.init();

    httpRequester = request(app.getHttpServer());
  });

  it('/ (GET weather)', async() => {
    const response = await httpRequester.get('/Bray-Dunes').expect(200);
    expect(response.body).toEqual(expect.any(Object));
  });
});
