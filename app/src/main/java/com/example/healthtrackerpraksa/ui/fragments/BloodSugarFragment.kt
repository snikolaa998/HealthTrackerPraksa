package com.example.healthtrackerpraksa.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.adapters.BloodSugarAdapter
import com.example.healthtrackerpraksa.adapters.CalendarAdapter
import com.example.healthtrackerpraksa.canvas.BloodSugarCanvas
import com.example.healthtrackerpraksa.persistence.model.BloodSugar
import com.example.healthtrackerpraksa.repository.Repository
import com.example.healthtrackerpraksa.viewModels.BloodPressureViewModelFactory
import com.example.healthtrackerpraksa.viewModels.BloodSugarViewModel
import com.example.healthtrackerpraksa.viewModels.BloodSugarViewModelFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BloodSugarFragment : Fragment() {

    private lateinit var bloodSugarViewModel: BloodSugarViewModel
    private lateinit var bloodSugarViewModelFactory: BloodSugarViewModelFactory

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_sugar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repo = Repository(activity?.application!!)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerViewBloodSugar)
        val canvas = view.findViewById<BloodSugarCanvas>(R.id.canvasSugar)
        bloodSugarViewModelFactory = BloodSugarViewModelFactory(repo)
        bloodSugarViewModel = ViewModelProvider(this, bloodSugarViewModelFactory).get(BloodSugarViewModel::class.java)
        bloodSugarViewModel.getBloodSugar()
        bloodSugarViewModel.allBloodSugar.observe(requireActivity(), androidx.lifecycle.Observer {
            val adapter = BloodSugarAdapter(it, requireContext())
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(activity)
            canvas.setParameter(it)
        })
    }
    
    fun insert(bloodSugar: BloodSugar) {
        bloodSugarViewModel.insert(bloodSugar)
    }
}