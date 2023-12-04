package com.example.chevalier_dutra_weather_app_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val weatherDataManager = WeatherDataManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()
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
                displayMapFragment()
                true
            }

            R.id.action_about-> {
                Log.d("MainActivity", "Clicked About Item")
                displayAboutFragment()
                true
            }

            // Handle other menu items as needed.
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun displayListFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        Log.d("MainActivity", "Displaying List Fragment")
        transaction.replace(
            R.id.main_layout_fragment,
            ListFragment.newInstance(weatherDataManager.getAllWeatherData())
        )
        transaction.commit()
        //floatingActionButton.visibility = View.VISIBLE
    }

    private fun displayMapFragment() {
        val transaction = supportFragmentManager.beginTransaction()
        Log.d("MainActivity", "Displaying Map Fragment")
        transaction.replace(
            R.id.main_layout_fragment,
            MapFragment.newInstance()
        )
        transaction.commit()
        //floatingActionButton.visibility = View.VISIBLE
    }

    private fun displayAboutFragment() {
        Log.d("MainActivity", "Displaying About Fragment")
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(
            R.id.main_layout_fragment,
            AboutFragment.newInstance()
        )
        transaction.commit()
        //floatingActionButton.visibility = View.VISIBLE
    }
    private fun initData() {
        weatherDataManager.addWeather(
            Weather(
                "Gardanne",
                "04/12/2023",
                "16h30",
                "4°C",
                "77%"
            )
        )
    }
}