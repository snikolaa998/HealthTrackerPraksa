package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.ui.fragments.adapters.BloodPressureAdapter
import com.example.healthtrackerpraksa.ui.viewmodels.BloodPressureViewModel
import kotlinx.android.synthetic.main.fragment_blood_pressure.*


class BloodPressureFragment : Fragment(R.layout.fragment_blood_pressure) {

    private val bloodPressureViewModel: BloodPressureViewModel by viewModels()
    private lateinit var bloodPressureAdapter: BloodPressureAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeObserver()
        bloodPressureViewModel.initBloodPressureLiveData()

        rv_blood_pressure.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
    }

    private fun subscribeObserver() {
        bloodPressureViewModel.bloodPressureLiveData
            .observe(viewLifecycleOwner,
                { listBloodPressure ->
                    bloodPressureAdapter = BloodPressureAdapter(listBloodPressure!!)
                    rv_blood_pressure.adapter = bloodPressureAdapter
                })
    }

}