package com.healthvista.app

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import java.util.Calendar

object SmartReminder {

    fun scheduleAllReminders(
        context: Context,
        medicineHour: Int = 20,
        medicineMinute: Int = 0,
        memeMode: Boolean = false
    ) {
        // Water: 9 AM, 11 AM, 1 PM, 3 PM, 5 PM
        val waterTimes = listOf(9, 11, 13, 15, 17)

        waterTimes.forEachIndexed { index, hour ->
            scheduleDaily(context, "water", 101 + index, hour, 0, memeMode)
        }

        // Break: 10 AM, 12 PM, 2 PM, 4 PM
        val breakTimes = listOf(10, 12, 14, 16)

        breakTimes.forEachIndexed { index, hour ->
            scheduleDaily(context, "break", 201 + index, hour, 0, memeMode)
        }

        // Exercise: 6 PM
        scheduleDaily(context, "exercise", 301, 18, 0, memeMode)

        // Sleep: 10 PM
        scheduleDaily(context, "sleep", 401, 22, 0, memeMode)

        // Medicine: 8 PM by default
        scheduleDaily(
            context, "medicine", 501,
            medicineHour, medicineMinute, memeMode
        )
    }

    private fun scheduleDaily(
        context: Context,
        type: String,
        id: Int,
        hour: Int,
        minute: Int,
        memeMode: Boolean
    ) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("reminder_type", type)
            putExtra("reminder_id", id)
            putExtra("meme_mode", memeMode)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)

            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    // Keep this function for your existing one-minute test button.
    fun scheduleReminder(
        context: Context,
        type: String = "general",
        reminderId: Int = 999,
        memeMode: Boolean = false
    ) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("reminder_type", type)
            putExtra("reminder_id", reminderId)
            putExtra("meme_mode", memeMode)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            reminderId,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 60_000,
            pendingIntent
        )
    }
}