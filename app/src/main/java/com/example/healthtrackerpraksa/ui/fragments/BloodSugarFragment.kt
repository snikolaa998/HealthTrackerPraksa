package com.example.healthtrackerpraksa.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.ui.fragments.adapters.BloodSugarAdapter
import com.example.healthtrackerpraksa.ui.viewmodels.BloodSugarViewModel
import kotlinx.android.synthetic.main.fragment_blood_sugar.*
import java.util.*


class BloodSugarFragment : Fragment(R.layout.fragment_blood_sugar) {

    private val bloodSugarViewModel: BloodSugarViewModel by viewModels()
    private lateinit var bloodSugarAdapter: BloodSugarAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bloodSugarViewModel.initBloodSugarLiveData()

        rv_blood_sugar.layoutManager =
            LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)

        subscribeObserver()
    }

    private fun subscribeObserver() {
        bloodSugarViewModel.bloodSugarLiveData.observe(
            viewLifecycleOwner,
            { bloodSugarList ->
                bloodSugarAdapter = BloodSugarAdapter(bloodSugarList!!)
                rv_blood_sugar.adapter = bloodSugarAdapter
            })
    }

}