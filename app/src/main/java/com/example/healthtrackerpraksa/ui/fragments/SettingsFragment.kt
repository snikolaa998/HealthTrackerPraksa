package com.example.healthtrackerpraksa.ui.fragments

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.example.healthtrackerpraksa.R


class SettingsFragment : Fragment() {
    private lateinit var sharedPreferences: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonSave = view.findViewById<Button>(R.id.btn_save_fragment_settings)
        buttonSave.setOnClickListener {
            Toast.makeText(requireContext(), "Button Saveee", Toast.LENGTH_SHORT).show()
        }
    }
}