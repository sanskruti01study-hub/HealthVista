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
import androidx.compose.runtime.Composable
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Background)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(Green, RoundedCornerShape(15.dp)),
                contentAlignment = Alignment.Center
            ) {
                Text("H", color = Color.White, fontSize = 27.sp,
                    fontWeight = FontWeight.Bold)
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text("HealthVista", fontSize = 23.sp,
                    fontWeight = FontWeight.Bold, color = Dark)
                Text("Your wellness companion",
                    fontSize = 12.sp, color = Green)
            }
        }

        Spacer(modifier = Modifier.height(28.dp))

        Text("Hello, there! 👋",
            fontSize = 27.sp, fontWeight = FontWeight.Bold, color = Dark)

        Text("Let's take care of your health today.",
            fontSize = 14.sp, color = Color.Gray)

        Spacer(modifier = Modifier.height(24.dp))

        Card(
            colors = CardDefaults.cardColors(
                containerColor = Green
            ),
            shape = RoundedCornerShape(22.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(22.dp)) {
                Text("YOUR DAILY WELLNESS",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp, fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(12.dp))

                Text("Small steps,\nhealthier life!",
                    color = Color.White, fontSize = 27.sp,
                    fontWeight = FontWeight.Bold)

                Spacer(modifier = Modifier.height(8.dp))

                Text("Build healthy habits one day at a time.",
                    color = Color.White, fontSize = 14.sp)
            }
        }

        Spacer(modifier = Modifier.height(25.dp))

        Text("Today's Overview", fontSize = 20.sp,
            fontWeight = FontWeight.Bold, color = Dark)

        Spacer(modifier = Modifier.height(14.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            WellnessCard("💧", "Water Intake", "0 / 8 glasses",
                Modifier.weight(1f))
            WellnessCard("👟", "Activity", "Not recorded",
                Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            WellnessCard("🥗", "Nutrition", "Not recorded",
                Modifier.weight(1f))
            WellnessCard("😴", "Sleep", "Not recorded",
                Modifier.weight(1f))
        }

        Spacer(modifier = Modifier.height(26.dp))

        Text("Healthy Lifestyle Tips 🌱",
            fontSize = 20.sp, fontWeight = FontWeight.Bold,
            color = Dark)

        Spacer(modifier = Modifier.height(12.dp))

        TipCard("💧 Stay Hydrated",
            "Drink water regularly throughout the day.")

        TipCard("🥦 Eat Balanced Meals",
            "Include fruits, vegetables and nutritious foods.")

        TipCard("🚶 Keep Moving",
            "Try walking and regular physical activity.")

        TipCard("🌙 Sleep Well",
            "Maintain a consistent sleep schedule.")

        Spacer(modifier = Modifier.height(25.dp))

        Text("HealthVista • Your health, your journey ❤️",
            fontSize = 13.sp, color = Green,
            modifier = Modifier.align(Alignment.CenterHorizontally))

        Spacer(modifier = Modifier.height(15.dp))
    }
}

@Composable
fun WellnessCard(
    emoji: String,
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(18.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(15.dp)) {
            Text(emoji, fontSize = 25.sp)
            Spacer(modifier = Modifier.height(9.dp))
            Text(title, fontWeight = FontWeight.Bold,
                color = Dark, fontSize = 14.sp)
            Spacer(modifier = Modifier.height(5.dp))
            Text(value, color = Green, fontSize = 12.sp)
        }
    }
}

@Composable
fun TipCard(title: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        shape = RoundedCornerShape(15.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, fontSize = 15.sp,
                fontWeight = FontWeight.Bold, color = Dark)
            Spacer(modifier = Modifier.height(5.dp))
            Text(description, fontSize = 13.sp,
                color = Color.Gray)
        }
    }
}
