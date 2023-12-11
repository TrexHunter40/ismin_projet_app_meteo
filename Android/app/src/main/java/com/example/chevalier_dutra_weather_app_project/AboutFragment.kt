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
            openWebLink(getString(R.string.data_link))
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