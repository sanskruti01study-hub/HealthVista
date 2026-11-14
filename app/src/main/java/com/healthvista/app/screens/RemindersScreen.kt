package com.healthvista.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun RemindersScreen() {

    var waterReminder by remember { mutableStateOf(true) }
    var sleepReminder by remember { mutableStateOf(true) }
    var activityReminder by remember { mutableStateOf(false) }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {

            Text(
                text = "My Reminders",
                fontSize = 26.sp,
                color = Color(0xFF1565C0)
            )

            Spacer(modifier = Modifier.height(20.dp))

            ReminderItem(
                title = "Drink Water",
                checked = waterReminder,
                onCheckedChange = { waterReminder = it }
            )

            ReminderItem(
                title = "Sleep on Time",
                checked = sleepReminder,
                onCheckedChange = { sleepReminder = it }
            )

            ReminderItem(
                title = "Daily Activity",
                checked = activityReminder,
                onCheckedChange = { activityReminder = it }
            )
        }
    }
}

@Composable
fun ReminderItem(
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFE3F2FD)
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = title,
                fontSize = 18.sp
            )

            Switch(
                checked = checked,
                onCheckedChange = onCheckedChange
            )
        }
    }
}