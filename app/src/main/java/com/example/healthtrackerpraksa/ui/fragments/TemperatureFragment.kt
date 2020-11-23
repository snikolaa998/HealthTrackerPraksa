package com.example.healthtrackerpraksa.ui.fragments

import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.Temperature
import com.example.healthtrackerpraksa.ui.fragments.adapters.TemperatureAdapter
import com.example.healthtrackerpraksa.ui.viewmodels.HealthTrackerViewModel


class TemperatureFragment : Fragment(R.layout.fragment_temperature) {

    private val healthTrackerViewModel: HealthTrackerViewModel by activityViewModels()
    private lateinit var temperatureRecyclerView: RecyclerView
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        temperatureRecyclerView = view.findViewById<RecyclerView>(R.id.rv_temperature)
        temperatureRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        subscribeObserver()

    }

    private fun subscribeObserver() {
        healthTrackerViewModel.getAllTemperatures()
            .observe(viewLifecycleOwner,
                { temperatureList ->
                    val tempAdapter = TemperatureAdapter(temperatureList!!)
                    temperatureRecyclerView.adapter = tempAdapter
                })
    }
}