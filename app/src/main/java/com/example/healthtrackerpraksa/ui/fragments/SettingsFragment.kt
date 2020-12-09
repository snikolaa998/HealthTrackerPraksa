package com.example.healthtrackerpraksa.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle

import android.view.View
import androidx.fragment.app.Fragment
import com.example.healthtrackerpraksa.R

class SettingsFragment : Fragment(R.layout.fragment_settings) {

//    val prefs: SharedPreferences =
    var unitOfMeasure = "C"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


//        prefs.edit().putString("unitOfMeasure", unitOfMeasure)

    }

}