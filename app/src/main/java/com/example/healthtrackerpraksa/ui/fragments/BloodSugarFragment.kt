package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.model.BloodSugar
import com.example.healthtrackerpraksa.ui.fragments.adapters.BloodSugarAdapter
import com.example.healthtrackerpraksa.ui.viewmodels.HealthTrackerViewModel
import kotlinx.android.synthetic.main.fragment_blood_sugar.*
import java.util.*


class BloodSugarFragment : Fragment(R.layout.fragment_blood_sugar) {

    private val viewModel: HealthTrackerViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rv_blood_sugar.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        subscribeObserver()
    }

    private fun subscribeObserver() {
        viewModel.getAllBloodSugar().observe(
            viewLifecycleOwner,
            { bloodSugarList ->
                val bloodSugarAdapter = BloodSugarAdapter(bloodSugarList!!)
                rv_blood_sugar.adapter = bloodSugarAdapter
            })
    }

}