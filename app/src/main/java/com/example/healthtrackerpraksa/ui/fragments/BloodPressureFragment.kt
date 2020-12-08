package com.example.healthtrackerpraksa.ui.fragments

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.adapters.BloodPressureAdapter
import com.example.healthtrackerpraksa.canvas.BloodPressureCanvas
import com.example.healthtrackerpraksa.interfaces.DataIsReady
import com.example.healthtrackerpraksa.persistence.model.BloodPressure
import com.example.healthtrackerpraksa.repository.Repository
import com.example.healthtrackerpraksa.ui.BloodPressurePopUp
import com.example.healthtrackerpraksa.ui.MainActivity
import com.example.healthtrackerpraksa.viewModels.BloodPressureViewModel
import com.example.healthtrackerpraksa.viewModels.BloodPressureViewModelFactory
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class BloodPressureFragment : Fragment() {

    private lateinit var bloodPressureViewModel: BloodPressureViewModel
    private lateinit var bloodPressureViewModelFactory: BloodPressureViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_pressure, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerView = view?.findViewById<RecyclerView>(R.id.bloodPressureRecyclerView)
        val repo = Repository(activity?.application!!)
        val canvas = view.findViewById<BloodPressureCanvas>(R.id.canvasPressure)
        bloodPressureViewModelFactory = BloodPressureViewModelFactory(repo)
        bloodPressureViewModel = ViewModelProvider(this, bloodPressureViewModelFactory).get(BloodPressureViewModel::class.java)
        bloodPressureViewModel.getBloodPresure()
        bloodPressureViewModel.allBloodPressure.observe(requireActivity(), androidx.lifecycle.Observer {
            val adapter = BloodPressureAdapter(it, requireActivity())
            recyclerView?.adapter = adapter
            recyclerView?.layoutManager = LinearLayoutManager(activity)
            canvas.setParameter(it)
        })
    }

    fun insert(bloodPressure: BloodPressure) {
        bloodPressureViewModel.insert(bloodPressure)
    }
}