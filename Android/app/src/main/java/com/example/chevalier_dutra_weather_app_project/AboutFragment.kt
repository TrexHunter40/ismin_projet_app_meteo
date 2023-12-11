package com.example.chevalier_dutra_weather_app_project

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment


class AboutFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_about, container, false)
        Log.d("AboutFragment", "onCreateView called")

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //Lien hypertexte vers la base de données opendata
        val dataLinkTextView: TextView = view.findViewById(R.id.f_about_text_link)
        dataLinkTextView.text = Html.fromHtml("<u>${getString(R.string.data_link)}</u>")
        dataLinkTextView.setOnClickListener {
            openWebLink("https://data.opendatasoft.com/explore/dataset/arome-0025-enriched%40public/map/?disjunctive.commune&disjunctive.code_commune&dataChart=eyJxdWVyaWVzIjpbeyJjb25maWciOnsiZGF0YXNldCI6ImFyb21lLTAwMjUtZW5yaWNoZWRAcHVibGljIiwib3B0aW9ucyI6eyJkaXNqdW5jdGl2ZS5jb21tdW5lIjp0cnVlLCJkaXNqdW5jdGl2ZS5jb2RlX2NvbW11bmUiOnRydWUsImxvY2F0aW9uIjoiNiw0Ni41MTM1MiwzLjQzODcyIiwiYmFzZW1hcCI6Imphd2cuc3RyZWV0cyJ9fSwiY2hhcnRzIjpbeyJhbGlnbk1vbnRoIjp0cnVlLCJ0eXBlIjoibGluZSIsImZ1bmMiOiJBVkciLCJ5QXhpcyI6IjJfbWV0cmVfdGVtcGVyYXR1cmUiLCJzY2llbnRpZmljRGlzcGxheSI6dHJ1ZSwiY29sb3IiOiIjMTQyRTdCIn1dLCJ4QXhpcyI6InRpbWVzdGFtcCIsIm1heHBvaW50cyI6IiIsInRpbWVzY2FsZSI6InllYXIiLCJzb3J0IjoiIn1dLCJkaXNwbGF5TGVnZW5kIjp0cnVlLCJhbGlnbk1vbnRoIjp0cnVlfQ%3D%3D&location=9,44.8695,-0.17715&basemap=jawg.streets")
        }
    }


    private fun openWebLink(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


    companion object {
        @JvmStatic
        fun newInstance() =
            AboutFragment()
    }
}