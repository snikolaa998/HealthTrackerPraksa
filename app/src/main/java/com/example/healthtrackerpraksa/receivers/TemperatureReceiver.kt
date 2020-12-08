package com.example.healthtrackerpraksa.receivers

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.healthtrackerpraksa.ui.MainActivity

class TemperatureReceiver : BroadcastReceiver() {

    private lateinit var mNotificationManager: NotificationManager
    private val TEMPERATURE_NOTIFICATION_ID = 2
    private val TEMPERATURE_CHANNEL = "temperature_notification"

    override fun onReceive(context: Context, intent: Intent) {
        mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        deliverNotification(context)
    }

    private fun deliverNotification(context: Context) {
        val temperatureIntent = Intent(context, MainActivity::class.java)
        val temperaturePendingIntent = PendingIntent.getActivity(
            context, TEMPERATURE_NOTIFICATION_ID, temperatureIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notification = Notification.Builder(context, TEMPERATURE_CHANNEL)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentText("Add temperature")
            .setContentTitle("Temperature Remainder")
            .setContentIntent(temperaturePendingIntent)
            .build()
        mNotificationManager.notify(TEMPERATURE_NOTIFICATION_ID, notification)
    }

}