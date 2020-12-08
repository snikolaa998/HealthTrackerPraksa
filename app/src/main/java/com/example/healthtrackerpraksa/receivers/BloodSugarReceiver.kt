package com.example.healthtrackerpraksa.receivers

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavDeepLinkBuilder
import com.example.healthtrackerpraksa.R
import com.example.healthtrackerpraksa.ui.MainActivity

class BloodSugarReceiver : BroadcastReceiver() {

    private lateinit var mNotificationManager: NotificationManager
    private val BLOOD_SUGAR_NOTIFICATION_ID = 0
    private val BLOOD_SUGAR_CHANNEL = "blood_sugar_notification"

    override fun onReceive(context: Context, intent: Intent) {
        mNotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        deliverNotification(context)
    }

    private fun deliverNotification(context: Context) {
        val sugarIntent = Intent(context, MainActivity::class.java)
//        val sugarPendingIntent = PendingIntent.getActivity(
//            context, BLOOD_SUGAR_NOTIFICATION_ID, sugarIntent, PendingIntent.FLAG_UPDATE_CURRENT
//        )

        val sugarPendingIntent = NavDeepLinkBuilder(context)
            .setComponentName(MainActivity::class.java)
            .setGraph(R.navigation.nav_graph)
            .setDestination(R.id.bloodSugarFragment)
            .createPendingIntent()

        val notification = Notification.Builder(context, BLOOD_SUGAR_CHANNEL)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentText("Add blood sugar")
            .setContentTitle("Blood Sugar Remainder")
            .setContentIntent(sugarPendingIntent)
            .build()
        mNotificationManager.notify(BLOOD_SUGAR_NOTIFICATION_ID, notification)
    }
}