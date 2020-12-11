package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.ui.fragments.adapters.TemperatureAdapter
import com.example.healthtrackerpraksa.ui.viewmodels.TemperatureViewModel
import com.example.healthtrackerpraksa.ui.customcomponents.uicomponents.temperaturegraph.GraphView


class TemperatureFragment : Fragment(R.layout.fragment_temperature) {

    private val temperatureViewModel: TemperatureViewModel by viewModels()
    private lateinit var temperatureRecyclerView: RecyclerView
    private lateinit var temperatureGraph: GraphView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        temperatureViewModel.initTemperatureLiveData()

        temperatureRecyclerView = view.findViewById(R.id.rv_temperature)
        temperatureRecyclerView.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        temperatureGraph = view.findViewById(R.id.temperature_graph)

        subscribeObserver()
    }

    private fun subscribeObserver() {
        temperatureViewModel.temperatureLiveData
            .observe(viewLifecycleOwner,
                { temperatureList ->
                    val tempAdapter = TemperatureAdapter(temperatureList)
                    temperatureRecyclerView.adapter = tempAdapter
                    if (temperatureList.isNotEmpty()) {
                        temperatureGraph.dataToDraw = temperatureList.reversed()
                    }
                    tempAdapter.notifyDataSetChanged()
                })
    }


}
