package com.healthvista.app

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

object NotificationHelper {

    private const val CHANNEL_ID = "health_reminders"

    fun showNotification(
        context: Context,
        type: String = "general",
        reminderId: Int = 101,
        memeMode:Boolean=false
    ) {
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "Health Reminders",
                NotificationManager.IMPORTANCE_HIGH
            )
            manager.createNotificationChannel(channel)
        }

        if (Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) return

        val message = if (memeMode) {
            BollywoodMemeMode.getReminderMessage(type)
        } else {
            when (type.lowercase()) {
                "water" -> "Time to drink water."
                "medicine" -> "Time for your scheduled medicine."
                "exercise" -> "Time to exercise."
                "sleep" -> "Time to rest."
                "break" -> "Time for a break."
                "checkup" -> "Time for your health checkup."
                else -> "Stay healthy!"
            }
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Health Vista Reminder")
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(reminderId, notification)
    }
}