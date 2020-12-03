package com.example.healthtrackerpraksa.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.example.healthtrackerpraksa.R


class SettingsFragment : Fragment() {
    private lateinit var mPreferences: SharedPreferences
    private lateinit var imageView: ImageView
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
        val buttonAddImage = view.findViewById<Button>(R.id.btnAddImage)
        imageView = view.findViewById(R.id.iv_fragment_settings)
        val name = view.findViewById<EditText>(R.id.et_name_fragment_settings)
        val lastName = view.findViewById<EditText>(R.id.et_last_name_fragment_settings)
        val phoneNum = view.findViewById<EditText>(R.id.et_phone_fragment_settings)
        mPreferences = activity?.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)!!
        name.setText(mPreferences.getString("name", "noValue"))
        lastName.setText(mPreferences.getString("lastName", "noValue"))
        phoneNum.setText(mPreferences.getString("phone", "noValue"))
//        imageView.setImageURI(Uri.parse(mPreferences.getString("photoPath", "noValue")))
        Glide.with(requireContext())
            .load(Uri.parse(mPreferences.getString("photoPath", "noValue")))
            .into(imageView)

        buttonAddImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (ActivityCompat.checkSelfPermission(requireContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
                    val permissions = arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE)
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    pickImageFromGallery()
                }
            } else {
                pickImageFromGallery()
            }
        }

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
    private fun pickImageFromGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        private val IMAGE_PICK_CODE = 1000
        private val PERMISSION_CODE = 1001
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    pickImageFromGallery()
                } else {
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            Glide.with(requireContext()).load(data?.data).into(imageView)
            val editor = mPreferences.edit()
            editor.putString("photoPath", data?.data.toString())
            editor.apply()
        }
    }
}