package com.healthvista.app


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
        val type = intent?.getStringExtra("reminder_type") ?: "general"
        val reminderId = intent?.getIntExtra("reminder_id", 101) ?: 101
        val memeMode = intent?.getBooleanExtra("meme_mode", false) ?: false

        NotificationHelper.showNotification(
            context,
            type,
            reminderId,
            memeMode
        )
    }
}


