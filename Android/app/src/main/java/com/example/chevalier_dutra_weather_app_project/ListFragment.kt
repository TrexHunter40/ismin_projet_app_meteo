package com.example.chevalier_dutra_weather_app_project

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



private const val ARG_WEATHERDATA = "param1"


class ListFragment : Fragment(), WeatherAdapter.WeatherListener {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var weatherData: ArrayList<Weather> = arrayListOf()

    private lateinit var weatherAdapter: WeatherAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            weatherData = it.getSerializable(ARG_WEATHERDATA) as ArrayList<Weather>
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        Log.d("ListFragment", "onCreateView called")

        recyclerView = view.findViewById(R.id.weather_data)

        weatherAdapter = WeatherAdapter(weatherData, activity as WeatherAdapter.WeatherListener)

        recyclerView.adapter = weatherAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(weatherData: ArrayList<Weather>) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_WEATHERDATA, weatherData)
                    /*
                    putString(ARG_PARAM2, param2)
                    putString(ARG_PARAM3, param3)
                    */

                }
            }
    }

    override fun onCityClicked(weather: Weather) {
        Log.d("ListFragment", "City row Clicked")
    }

    override fun onFavoriteClicked(weather: Weather) {
        Log.d("ListFragment", "Favorite button Clicked")
    }

}