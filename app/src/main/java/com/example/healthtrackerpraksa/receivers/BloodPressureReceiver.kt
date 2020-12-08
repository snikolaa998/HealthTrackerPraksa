package com.example.healthtrackerpraksa.receivers

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.healthtrackerpraksa.ui.MainActivity

class BloodPressureReceiver : BroadcastReceiver() {

    private lateinit var mNotificationManager: NotificationManager
    private val BLOOD_PRESSURE_NOTIFICATION_ID = 1
    private val BLOOD_PRESSURE_CHANNEL = "blood_pressure_notification"

    override fun onReceive(context: Context, intent: Intent) {
        mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        deliverNotification(context)
    }
    private fun deliverNotification(context: Context) {
        val bloodPressureIntent = Intent(context, MainActivity::class.java)
        val bloodPressurePendingIntent = PendingIntent.getActivity(
            context, BLOOD_PRESSURE_NOTIFICATION_ID, bloodPressureIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = Notification.Builder(context, BLOOD_PRESSURE_CHANNEL)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentText("Add Blood Pressure")
            .setContentTitle("Blood Pressure Remainder")
            .setContentIntent(bloodPressurePendingIntent)
            .build()
        mNotificationManager.notify(BLOOD_PRESSURE_NOTIFICATION_ID, notification)
    }
}