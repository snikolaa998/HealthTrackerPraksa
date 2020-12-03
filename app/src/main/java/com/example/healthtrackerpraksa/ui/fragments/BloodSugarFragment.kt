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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.adapters.CalendarAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class BloodSugarFragment : Fragment() {
    private lateinit var mPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_blood_sugar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mPreferences = activity?.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)!!
        val userName = mPreferences.getString("name", "noValue")
        val userLastName = mPreferences.getString("lastName", "noValue")
        val userPhone = mPreferences.getString("phone", "noValue")
        val photo = mPreferences.getString("photoPath", "noValue")
        Toast.makeText(requireContext(), "Photo: $photo Name: $userName, LastName: $userLastName, Phone: $userPhone", Toast.LENGTH_LONG).show()
    }
}