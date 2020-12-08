package com.example.healthtrackerpraksa.ui.fragments

import android.app.*
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.receivers.BloodPressureReceiver
import com.example.healthtrackerpraksa.receivers.BloodSugarReceiver
import com.example.healthtrackerpraksa.receivers.TemperatureReceiver


class SettingsFragment : Fragment() {

    private lateinit var mPreferences: SharedPreferences
    private lateinit var imageView: ImageView
    private lateinit var mNotificationManager: NotificationManager

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
        val name = view.findViewById<EditText>(R.id.et_name_fragment_settings)
        val lastName = view.findViewById<EditText>(R.id.et_last_name_fragment_settings)
        val phoneNum = view.findViewById<EditText>(R.id.et_phone_fragment_settings)
        val bloodSugarToggle = view.findViewById<ToggleButton>(R.id.bloodSugarToggle)
        val temperatureToggle = view.findViewById<ToggleButton>(R.id.temperatureToggle)
        val bloodPressureToggle = view.findViewById<ToggleButton>(R.id.bloodPressureToggle)
        imageView = view.findViewById(R.id.iv_fragment_settings)

        mNotificationManager = requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notifySugarIntent = Intent(requireContext(), BloodSugarReceiver::class.java)
        val notifySugarPendingIntent = PendingIntent.getBroadcast(
            requireContext(), BLOOD_SUGAR_NOTIFICATION_ID, notifySugarIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notifyTemperatureIntent = Intent(requireContext(), TemperatureReceiver::class.java)
        val notifyTemperaturePendingIntent = PendingIntent.getBroadcast(
            requireContext(), TEMPERATURE_NOTIFICATION_ID, notifyTemperatureIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notifyBloodPressureIntent = Intent(requireContext(), BloodPressureReceiver::class.java)
        val notifyBloodPressurePendingIntent = PendingIntent.getBroadcast(
            requireContext(), BLOOD_PRESSURE_NOTIFICATION_ID, notifyBloodPressureIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager
        createBloodSugarNotificationChannel()
        createTemperatureNotificationChannel()
        createBloodPressureNotificationChannel()

        mPreferences = activity?.getSharedPreferences("SHARED_PREF", Context.MODE_PRIVATE)!!

        name.setText(mPreferences.getString("name", "noValue"))
        lastName.setText(mPreferences.getString("lastName", "noValue"))
        phoneNum.setText(mPreferences.getString("phone", "noValue"))

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

        bloodSugarToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            val toastMessage: String
            if (isChecked) {
                val repeatInterval = 1000 * 10
                val triggerTime = SystemClock.elapsedRealtime() + repeatInterval
                toastMessage = "Blood Sugar Notification ON"
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval.toLong(), notifySugarPendingIntent)
            } else {
                alarmManager.cancel(notifySugarPendingIntent)
                toastMessage = "Blood Sugar Notification Off"
            }
            Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
        }

        temperatureToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            val toastMessage: String
            if (isChecked) {
                val repeatInterval = 1000 * 10
                val triggerTime = SystemClock.elapsedRealtime() + repeatInterval
                toastMessage = "Temperature Notification ON"
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval.toLong(), notifyTemperaturePendingIntent)
            } else {
                alarmManager.cancel(notifyTemperaturePendingIntent)
                toastMessage = "Temperature Notification Off"
            }
            Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
        }

        bloodPressureToggle.setOnCheckedChangeListener { buttonView, isChecked ->
            val toastMessage: String
            if (isChecked) {
                val repeatInterval = 1000 * 10
                val triggerTime = SystemClock.elapsedRealtime() + repeatInterval
                toastMessage = "Blood pressure notification On"
                alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerTime, repeatInterval.toLong(), notifyBloodPressurePendingIntent)
            } else {
                alarmManager.cancel(notifyBloodPressurePendingIntent)
                toastMessage = "Blood pressure notification Off"
            }
            Toast.makeText(requireContext(), toastMessage, Toast.LENGTH_SHORT).show()
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
        private val BLOOD_SUGAR_NOTIFICATION_ID = 0
        private val BLOOD_PRESSURE_NOTIFICATION_ID = 1
        private val TEMPERATURE_NOTIFICATION_ID = 2
        private val BLOOD_SUGAR_CHANNEL = "blood_sugar_notification"
        private val BLOOD_PRESSURE_CHANNEL = "blood_pressure_notification"
        private val TEMPERATURE_CHANNEL = "temperature_notification"
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

    private fun createBloodSugarNotificationChannel() {
        val bloodSugarChannel = NotificationChannel(BLOOD_SUGAR_CHANNEL, "Blood Sugar Notification", NotificationManager.IMPORTANCE_HIGH)
        bloodSugarChannel.apply {
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            description = "Blood Sugar Notification"
        }
        mNotificationManager.createNotificationChannel(bloodSugarChannel)
    }

    private fun createTemperatureNotificationChannel() {
        val temperatureChannel = NotificationChannel(TEMPERATURE_CHANNEL, "Temperature Notification", NotificationManager.IMPORTANCE_HIGH)
        temperatureChannel.apply {
            enableLights(true)
            lightColor = Color.RED
            enableVibration(true)
            description = "Temperature Notification"
        }
        mNotificationManager.createNotificationChannel(temperatureChannel)
    }

    private fun createBloodPressureNotificationChannel() {
        val bloodPressureChannel = NotificationChannel(BLOOD_PRESSURE_CHANNEL, "Blood Pressure Notification", NotificationManager.IMPORTANCE_HIGH)
        bloodPressureChannel.apply {
            enableVibration(true)
            enableLights(true)
            lightColor = Color.RED
            description = "Blood pressure notification"
        }
        mNotificationManager.createNotificationChannel(bloodPressureChannel)
    }
}