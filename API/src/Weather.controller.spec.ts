import { Test, TestingModule } from '@nestjs/testing';
import { INestApplication } from '@nestjs/common';
import * as request from 'supertest';
import { WeatherModule } from '../src/Weather.module';
import supertest from "supertest";
import * as http from "http";

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

  it('/GET weather', async() => {
    const response = await httpRequester.get('/Bray-Dunes').expect(200);
    expect(response.body).toEqual(expect.any(Object));
  });

  it('/POST weather', async() => {
    const response = await httpRequester
        .post('/weathers').send({
              pos: { lon: 4.20, lat: 42.00},
              city: 'La Capitale',
              date: '2023-12-08',
              time: '16:20:00+00:00',
              temperature: '26.50',
              humidity: '99.15',
              favorite: true,
            })
            .expect(201);
        expect(response.body).toEqual({
          pos: { lon: 4.20, lat: 42.00},
          city: 'La Capitale',
          date: '2023-12-08',
          time: '16:20:00+00:00',
          temperature: '26.50',
          humidity: '99.15',
          favorite: true,
        });
  });

    it('/DELETE weathers/:city', async() =>{
        await httpRequester
            .post('/weathers').send({
                pos: { lon: 4.20, lat: 42.00},
                city: 'La-Capitale',
                date: '2023-12-08',
                time: '16:20:00+00:00',
                temperature: '26.50',
                humidity: '99.15',
                favorite: true,
            });

        await httpRequester.delete('/weathers/La-Capitale').expect(200);

        const response = await httpRequester.get('/weather/La-Capitale').expect(404);
    });

    it('/GET all weather', async () => {
        const response = await httpRequester.get('/').expect(200);
        expect(response.body).toEqual(expect.any(Array));
        console.log("== Teste get all weathers ==")
        console.log(response.body);
    });

    it('/GET weather by city', async () => {
        const cityName = 'Bray-Dunes'; // Substitua pelo nome de uma cidade válida em seus dados
        const response = await httpRequester.get(`/${cityName}`).expect(200);
        expect(response.body.city).toEqual(cityName);
    });

    it('/PUT set favorite city', async () => {
        const cityName = 'Bray-Dunes'; // Substitua pelo nome de uma cidade válida em seus dados
        const response = await httpRequester
            .put(`/${cityName}`)
            .send({ favorite: true })
            .expect(200);
        expect(response.body.favorite).toEqual({"favorite": true});
    });

    it('/GET non-existent city should return 404', async () => {
        const nonExistentCity = 'NonExistentCity';
        await httpRequester.get(`/weather/${nonExistentCity}`).expect(404);
    });
});
