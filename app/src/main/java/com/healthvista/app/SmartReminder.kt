package com.healthvista.app

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object SmartReminder {

    fun scheduleReminder(
        context: Context,
        type: String = "general",
        reminderId: Int = 101,
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

        val triggerTime = System.currentTimeMillis() + 60_000

        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            triggerTime,
            pendingIntent
        )
    }
}