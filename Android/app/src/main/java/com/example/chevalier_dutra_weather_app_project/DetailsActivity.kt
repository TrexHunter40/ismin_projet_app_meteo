package com.example.chevalier_dutra_weather_app_project

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


private const val ARG_WEATHER = "weather"

class DetailsActivity : AppCompatActivity() {


    private var weather: Weather? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val title: TextView =  findViewById(R.id.a_details_city)
        val date: TextView =  findViewById(R.id.a_details_date)
        val time: TextView =  findViewById(R.id.a_details_time)
        val temperature: TextView =  findViewById(R.id.a_details_temperature)
        val humidity: TextView =  findViewById(R.id.a_details_humidity)
        val wind: TextView =  findViewById(R.id.a_details_wind)

        if (intent.hasExtra("weather")) {
            weather = intent.getSerializableExtra("weather") as? Weather

            weather?.let {
                title.text = it.city
                date.text = "Date: " + it.date
                time.text = "Time: " + it.time
                temperature.text = "Temperature: " + it.temperature
                humidity.text = "Humidity: " + it.humidity
                //wind.text = "Wind speed: " + it.wind

            }
        }
        else
            Log.e("DetailsActivity", "No weather passed to activity.")
    }

    /*
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(context: Context, weather: Weather) =
            Intent(context, DetailsActivity::class.java).apply {
                putExtra(ARG_WEATHER, weather)
            }
    }
    */

    /*
    companion object {
        fun newInstance(context: Context, weather: Weather): Intent {
            Log.d("Details", "newInstance called. weather = $weather")
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("weather", weather)
            return intent
        }
    }

     */

}