package com.example.healthtrackerpraksa.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.healthtrackerpraksa.R


class SettingsFragment : Fragment() {
    private lateinit var mPreferences: SharedPreferences
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
        val name = view.findViewById<EditText>(R.id.et_name_fragment_settings)
        val lastName = view.findViewById<EditText>(R.id.et_last_name_fragment_settings)
        val phoneNum = view.findViewById<EditText>(R.id.et_phone_fragment_settings)
        mPreferences = activity?.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)!!
        buttonSave.setOnClickListener {
            val userName = name.text.toString()
            val userLastName = lastName.text.toString()
            val userPhone = phoneNum.text.toString()
            val editor = mPreferences.edit()
            editor.putString("name", userName)
            editor.putString("lastName", userLastName)
            editor.putString("phone", userPhone)
            editor.apply()
        }
    }
}