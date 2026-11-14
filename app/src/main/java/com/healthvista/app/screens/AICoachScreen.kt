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
fun AICoachScreen() {

    var userInput by remember { mutableStateOf("") }

    Scaffold { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
        ) {

            Text(
                text = "AI Wellness Coach",
                fontSize = 26.sp,
                color = Color(0xFF1565C0)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFFE3F2FD)
                ),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(
                    text = "Hello! 👋\n\nYour wellness score is 78/100.\nTry getting 8 hours of sleep, staying hydrated, and walking regularly.",
                    modifier = Modifier.padding(20.dp),
                    fontSize = 17.sp
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = userInput,
                onValueChange = { userInput = it },
                label = { Text("Ask your wellness question") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = { userInput = "" },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Send")
            }
        }
    }
}