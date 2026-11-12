package com.healthvista.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthvista.app.ui.theme.HealthVistaTheme

private val Green = Color(0xFF267A59)
private val Background = Color(0xFFF5F8F4)
private val Dark = Color(0xFF20352B)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HealthVistaTheme {
                HealthVistaApp()
            }
        }
    }
}

@Composable
fun HealthVistaApp() {

    var water by rememberSaveable { mutableStateOf(0) }
    var activity by rememberSaveable { mutableStateOf(false) }
    var nutrition by rememberSaveable { mutableStateOf(false) }
    var sleep by rememberSaveable { mutableStateOf(7f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {

        Text(
            "🟩 HealthVista",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold,
            color = Green
        )

        Text("Your wellness companion", color = Green)

        Spacer(modifier = Modifier.height(25.dp))

        Text("Hello, there! 👋", fontSize = 28.sp,
            fontWeight = FontWeight.Bold, color = Dark)

        Text("Let's take care of your health today.")

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(22.dp)) {
                Text("YOUR DAILY WELLNESS", color = Color.White)
                Spacer(modifier = Modifier.height(15.dp))
                Text(
                    "Small steps,\nhealthier life!",
                    fontSize = 27.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    "Build healthy habits one day at a time.",
                    color = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text("Today's Overview", fontSize = 22.sp,
            fontWeight = FontWeight.Bold, color = Dark)

        Spacer(modifier = Modifier.height(12.dp))

        // WATER TRACKER
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("💧 Water Intake", fontSize = 19.sp,
                    fontWeight = FontWeight.Bold)
                Text("$water / 8 glasses")
                LinearProgressIndicator(
                    progress = { water / 8f },
                    modifier = Modifier.fillMaxWidth(),
                    color = Green
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    OutlinedButton(onClick = {
                        if (water > 0) water--
                    }) {
                        Text("-")
                    }
                    Spacer(modifier = Modifier.width(12.dp))
                    Button(onClick = {
                        if (water < 8) water++
                    }) {
                        Text("+ Add Glass")
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // ACTIVITY TRACKER
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("👟 Activity", fontSize = 19.sp,
                    fontWeight = FontWeight.Bold)
                Text(if (activity) "Activity completed!" else "Not recorded")
                Button(onClick = {
                    activity = !activity
                }) {
                    Text(if (activity) "Undo" else "Mark Completed")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // NUTRITION TRACKER
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("🥗 Nutrition", fontSize = 19.sp,
                    fontWeight = FontWeight.Bold)
                Text(if (nutrition) "Healthy meal recorded!" else "Not recorded")
                Button(onClick = {
                    nutrition = !nutrition
                }) {
                    Text(if (nutrition) "Undo" else "Record Healthy Meal")
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // SLEEP TRACKER
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("😴 Sleep Tracker", fontSize = 19.sp,
                    fontWeight = FontWeight.Bold)
                Text("${sleep.toInt()} hours of sleep")
                Slider(
                    value = sleep,
                    onValueChange = { sleep = it },
                    valueRange = 0f..12f
                )
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text("Healthy Lifestyle Tips 🌱",
            fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(12.dp))

        listOf(
            "💧 Stay Hydrated" to "Drink water regularly throughout the day.",
            "🥦 Eat Balanced Meals" to "Include fruits and vegetables.",
            "🚶 Keep Moving" to "Try walking and regular physical activity.",
            "🌙 Sleep Well" to "Maintain a consistent sleep schedule."
        ).forEach { tip ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(tip.first, fontWeight = FontWeight.Bold)
                    Text(tip.second)
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            "HealthVista • Your health, your journey ❤️",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Green
        )
    }
}
