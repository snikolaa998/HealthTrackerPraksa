package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.BloodPressure
import com.example.healthtrackerpraksa.ui.fragments.adapters.BloodPressureAdapter
import com.example.healthtrackerpraksa.ui.fragments.adapters.BloodSugarAdapter
import com.example.healthtrackerpraksa.ui.viewmodels.BloodPressureViewModel
import com.example.healthtrackerpraksa.ui.viewmodels.HealthTrackerViewModel
import kotlinx.android.synthetic.main.fragment_blood_pressure.*
import java.util.*


class BloodPressureFragment : Fragment(R.layout.fragment_blood_pressure) {

    private val bloodPressureViewModel: BloodPressureViewModel by viewModels()
    private lateinit var bloodPressureAdapter: BloodPressureAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bloodPressureViewModel.initBloodPressureLiveData()

        rv_blood_pressure.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        subscribeObserver()
    }

    private fun subscribeObserver() {
        bloodPressureViewModel.bloodPressureLiveData
            .observe(viewLifecycleOwner, object : Observer<List<BloodPressure>> {
                override fun onChanged(listBloodPressure: List<BloodPressure>?) {
                    bloodPressureAdapter = BloodPressureAdapter(listBloodPressure!!)
                    rv_blood_pressure.adapter = bloodPressureAdapter
                }
            })
    }

}