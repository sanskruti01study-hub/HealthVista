package com.healthvista.app

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.healthvista.app.voice.VoiceAssistant

@Composable
fun VoiceAssistantSection() {

    val context = LocalContext.current

    var recognizedText by remember {
        mutableStateOf("Say something...")
    }

    var status by remember {
        mutableStateOf("Voice assistant ready")
    }
    var elderMode by remember { mutableStateOf(false) }
    var memeMode by remember { mutableStateOf(false) }
    val assistant = remember {
        VoiceAssistant(
            context = context,
            onCommandRecognized = {
                recognizedText = it
                status = "Command received"
            },
            onError = {
                status = it
            }
        )
    }

    // Microphone permission
    val permissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                assistant.startListening()
                status = "Listening..."
            } else {
                status = "Microphone permission denied"
            }
        }

    // Notification permission
    val notificationPermissionLauncher =
        rememberLauncherForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { granted ->
            if (granted) {
                SmartReminder.scheduleReminder(context)
                status = "Reminder scheduled for one minute"
            } else {
                status = "Notification permission denied"
            }
        }

    DisposableEffect(assistant) {
        onDispose {
            assistant.release()
        }
    }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {

        Text("Health Vista Voice Assistant")

        Text(status)

        Text("You said: $recognizedText")

        // Voice assistant button
        Button(onClick = {
            val permission = ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.RECORD_AUDIO
            )

            if (permission == PackageManager.PERMISSION_GRANTED) {
                assistant.startListening()
                status = "Listening..."
            } else {
                permissionLauncher.launch(
                    Manifest.permission.RECORD_AUDIO
                )
            }
        }) {
            Text("Start Voice Assistant")
        }

        // Voice reply test button
        Button(onClick = {
            assistant.speak(
                "Hello! I am Health Vista. How can I help you?",
                elderMode = elderMode
            )
        }) {
            Text("Test Voice Reply")
        }
        Button(onClick = {
            elderMode = !elderMode
            assistant.elderModeEnabled = elderMode
        }) {
            Text(if (elderMode) "Elder Mode ON" else "Enable Elder Mode")
        }
        Button(onClick = {
            memeMode = !memeMode
        }) {
            Text(if (memeMode) "Bollywood Meme Mode ON" else "Enable Bollywood Meme Mode")
        }
        // Smart reminder button
        Button(onClick = {

            if (Build.VERSION.SDK_INT >= 33 &&
                ContextCompat.checkSelfPermission(
                    context,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                notificationPermissionLauncher.launch(
                    Manifest.permission.POST_NOTIFICATIONS
                )
            } else {
                SmartReminder.scheduleReminder(context, "water", 1, memeMode)
                SmartReminder.scheduleReminder(context, "medicine", 2, memeMode)
                SmartReminder.scheduleReminder(context, "exercise", 3, memeMode)
                SmartReminder.scheduleReminder(context, "sleep", 4, memeMode)
                SmartReminder.scheduleReminder(context, "break", 5, memeMode)
                status = "All 5 reminders scheduled for one minute"
            }

        }) {
            Text("Test Smart Reminder")
        }
    }
}