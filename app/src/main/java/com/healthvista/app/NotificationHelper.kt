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

    private const val CHANNEL_ID = "healthvista_reminders"

    fun showNotification(
        context: Context,
        type: String,
        reminderId: Int,
        memeMode: Boolean = false
    ) {
        createNotificationChannel(context)

        if (Build.VERSION.SDK_INT >= 33 &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }

        val (title, normalMessage, funnyMessage) = when (type.lowercase()) {

            "water" -> Triple(
                "Water Reminder",
                "Time to drink some water. Stay hydrated!",
                "Picture abhi baaki hai! Drink some water."
            )

            "exercise" -> Triple(
                "Activity Reminder",
                "Take a little time to move and stay active.",
                "Interval khatam! Time to move."
            )

            "sleep" -> Triple(
                "Sleep Reminder",
                "It's time to wind down and get enough rest.",
                "The show must end! Time to rest."
            )

            "medicine" -> Triple(
                "Medicine Reminder",
                "Reminder: Check your prescribed medicine schedule.",
                "Health first! Check your medicine schedule."
            )

            "break" -> Triple(
                "Break Reminder",
                "Take a short break and relax.",
                "Even heroes need a break!"
            )

            "checkup" -> Triple(
                "Health Checkup Reminder",
                "Don't forget your scheduled health checkup.",
                "Your health deserves a priority pass!"
            )

            else -> Triple(
                "HealthVista Reminder",
                "Time to take care of your health.",
                "Stay healthy, stay happy!"
            )
        }

        val message = if (memeMode) funnyMessage else normalMessage

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context)
            .notify(reminderId, notification)
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "HealthVista Reminders",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Personalized health and lifestyle reminders"
            }

            val manager = context.getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager

            manager.createNotificationChannel(channel)
        }
    }
}