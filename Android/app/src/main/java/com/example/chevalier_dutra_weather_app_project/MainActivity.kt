package com.example.chevalier_dutra_weather_app_project

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


private const val SERVER_BASE_URL = "https://Project-Weather-LCR-CDO.cleverapps.io/"

class MainActivity : AppCompatActivity(), WeatherAdapter.WeatherListener {

    private lateinit var weatherAdapter: WeatherAdapter
    private val weatherDataManager = WeatherDataManager()

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(SERVER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    private val weatherService = retrofit.create(WeatherService::class.java)

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if( it.resultCode == RESULT_OK) {
                val weather = it.data?.getSerializableExtra(CREATED_WEATHER) as Weather
                weatherDataManager.addWeather(weather)
                weatherAdapter.updateWeatherData(weatherDataManager.getAllWeatherData())
            }
        }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("MainActivity", "Downloading data")
        refreshWeatherData()

        /* uncomment this to have offline data (and comment refreshWeatherData())
        //initData()
        //setContentView(R.layout.activity_main)
        //displayListFragment()
        */

        findViewById<FloatingActionButton>(R.id.a_main_btn_refresh_data).setOnClickListener {
            refreshWeatherData()
        }
    }

    override fun onCreateOptionsMenu(currentMenu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, currentMenu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list-> {
                Log.d("MainActivity", "Clicked List Item")
                displayListFragment()
                true
            }

            R.id.action_map-> {
                Log.d("MainActivity", "Clicked Map Item")
                if(weatherDataManager.getAllWeatherData().size != 0) {
                    displayMapFragment()
                    return true
                } else {
                    Log.e("MainActivity", "ERROR while accessing map fragment: no weather data")
                    Toast.makeText(this@MainActivity, "No data to show", Toast.LENGTH_SHORT).show()
                    return false
                }
            }

            R.id.action_about-> {
                Log.d("MainActivity", "Clicked About Item")
                displayAboutFragment()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onCityClicked(weather: Weather) {
        Log.d("MainActivity", "City Clicked. Opening Details Activity.")
        val intent = Intent(this, DetailsActivity::class.java)
        intent.putExtra("weather", weather)
        this.startActivity(intent)
    }

    override fun onFavoriteClicked(weather: Weather) {
        System.out.println("Favorite button Clicked. Sending data to server.")

        weatherService.setFavoriteCity(weather.city, !weather.favorite)
            .enqueue(object : Callback<Weather> {
                override fun onResponse(
                    call: Call<Weather>,
                    response: Response<Weather>
                ) {
                    val weather: Weather? = response.body()
                    if (response.body() != null) {
                        Log.d("MainActivity", "Favorite switched")
                        weatherDataManager.addWeather(weather!!)
                        displayListFragment()
                        Toast.makeText(this@MainActivity, "Favorite switched", Toast.LENGTH_SHORT).show()
                    } else
                        Log.e("MainActivity", "Response weather for favorite is null.")
                }

                override fun onFailure(call: Call<Weather>, t: Throwable) {
                    // DO THINGS
                    System.out.println("Failed to switch to favorites")
                    Toast.makeText(this@MainActivity, "Failed to switch favorite", Toast.LENGTH_SHORT).show()
                }
            })


    }


    private fun displayListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        Log.d("MainActivity", "Displaying List Fragment")
        transaction.replace(
            R.id.main_layout_fragment,
            ListFragment.newInstance(weatherDataManager.getAllWeatherData())
        )
        transaction.commit()
    }

    private fun displayMapFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        Log.d("MainActivity", "Displaying Map Fragment")
        transaction.replace(
            R.id.main_layout_fragment,
            GoogleMapFragment.newInstance(weatherDataManager.getAllWeatherData())
        )
        transaction.commit()
    }

    private fun displayAboutFragment() {
        Log.d("MainActivity", "Displaying About Fragment")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.main_layout_fragment,
            AboutFragment.newInstance()
        )
        transaction.commit()
    }

    private fun refreshWeatherData() {
        Log.d("MainActivity", "Refreshing WeatherData")
        weatherDataManager.clear()
        Log.d("MainActivity", "Downloading data")
        weatherService.getAllWeather()
            .enqueue(object : Callback<List<Weather>> {
                override fun onResponse(
                    call: Call<List<Weather>>,
                    response: Response<List<Weather>>
                ) {
                    Log.d("MainActivity", "We have a response.")
                    val weatherData: List<Weather>? = response.body()
                    Log.d("MainActivity", "Raw JSON response: ${response.raw()}")
                    if (response.body() != null) {
                        Log.d("MainActivity", "Response weatherData is not null.")
                        weatherDataManager.addWeatherData(weatherData!!)
                        displayListFragment()
                        Toast.makeText(this@MainActivity, "Refreshed", Toast.LENGTH_SHORT).show()
                    } else
                        Log.e("MainActivity", "Response weatherData is null.")

                }

                override fun onFailure(call: Call<List<Weather>>, t: Throwable) {
                    Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
                    Log.e("Retrofit", "Error: ${t.message}")
                    t.printStackTrace()
                }
            })

        try {
            val response = weatherService.getAllWeather().execute()
            if (response.isSuccessful) {
                val weatherData: List<Weather>? = response.body()
                Log.d("MainActivity", "Raw JSON response: ${response.raw()}")
            } else {
                Log.e("MainActivity", "Retrofit Call Unsuccessful: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("MainActivity", "Retrofit Call Exception: ${e.message}")
        }
    }



    private fun initData() {

        weatherDataManager.addWeather(
            Weather(
                Position(43.455672, 5.471045),
                "Gardanne",
                "04/12/2023",
                "16h30",
                4.toDouble(),
                77.toDouble(),
                true
            )
        )

        weatherDataManager.addWeather(
            Weather(
                Position(48.648750, -2.025740),
                "Saint-Malo",
                "04/12/2023",
                "16h30",
                (-1).toDouble(),
                95.toDouble(),
                false
            )
        )

        weatherDataManager.addWeather(
            Weather(
                Position(43.886284, -0.500786),
                "Mont-de-Marsan",
                "04/12/2023",
                "16h30",
                5.toDouble(),
                65.toDouble(),
                false
            )
        )

        weatherDataManager.addWeather(
            Weather(
                Position(44.840249, -0.573871),
                "Bordeaux",
                "04/12/2023",
                "16h30",
                4.toDouble(),
                70.toDouble(),
                false
            )
        )

        weatherDataManager.addWeather(
            Weather(
                Position(43.297686, 5.380224),
                "Marseille",
                "04/12/2023",
                "16h30",
                8.toDouble(),
                55.toDouble(),
                false
            )
        )

        weatherDataManager.addWeather(
            Weather(
                Position(43.528626, 5.447987),
                "Aix-en-Provence",
                "04/12/2023",
                "16h30",
                7.toDouble(),
                66.toDouble(),
                true
            )
        )

        weatherDataManager.addWeather(
            Weather(
                Position(-8.335099, 115.106876),
                "Bali",
                "04/12/2023",
                "16h30",
                32.toDouble(),
                35.toDouble(),
                false
            )
        )

        weatherDataManager.addWeather(
            Weather(
                Position(.762597, -80.214356),
                "Miami",
                "04/12/2023",
                "16h30",
                24.toDouble(),
                10.toDouble(),
                false
            )
        )

    }
}