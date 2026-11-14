package com.healthvista.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Green = Color(0xFF2E7D6B)
private val LightGreen = Color(0xFFE8F5F0)
private val Background = Color(0xFFF5F8F7)
private val DarkText = Color(0xFF18332C)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            HealthVistaApp()
        }
    }
}

@Composable
fun HealthVistaApp() {

    var selectedTab by remember { mutableStateOf(0) }

    val tabs = listOf(
        "Home",
        "Progress",
        "AI Coach",
        "Reminders",
        "Profile"
    )

    val icons = listOf(
        Icons.Filled.Home,
        Icons.Filled.ShowChart,
        Icons.Filled.Chat,
        Icons.Filled.Notifications,
        Icons.Filled.Person
    )

    MaterialTheme(
        colorScheme = lightColorScheme(
            primary = Green,
            background = Background,
            surface = Color.White
        )
    ) {
        Scaffold(
            containerColor = Background,
            bottomBar = {
                NavigationBar(
                    containerColor = Color.White
                ) {
                    tabs.forEachIndexed { index, title ->

                        NavigationBarItem(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            icon = {
                                Icon(
                                    imageVector = icons[index],
                                    contentDescription = title
                                )
                            },
                            label = {
                                Text(title, fontSize = 10.sp)
                            }
                        )
                    }
                }
            }
        ) { padding ->

            when (selectedTab) {

                0 -> HomeScreen(
                    modifier = Modifier.padding(padding)
                )

                1 -> PlaceholderScreen(
                    "Progress",
                    "Your health trends will appear here.",
                    Modifier.padding(padding)
                )

                2 -> PlaceholderScreen(
                    "AI Coach",
                    "Your personal wellness assistant.",
                    Modifier.padding(padding)
                )

                3 -> PlaceholderScreen(
                    "Smart Reminders",
                    "Your health reminders will appear here.",
                    Modifier.padding(padding)
                )

                4 -> PlaceholderScreen(
                    "Profile",
                    "Manage your personal information.",
                    Modifier.padding(padding)
                )
            }
        }
    }
}

// ---------------- HOME SCREEN ----------------

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        contentPadding = PaddingValues(vertical = 24.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {

        // Header
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Health Vista",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = Green
                    )

                    Text(
                        text = "Good morning!",
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                }

                IconButton(onClick = {}) {
                    Icon(
                        Icons.Filled.AccountCircle,
                        contentDescription = "Profile",
                        tint = Green,
                        modifier = Modifier.size(36.dp)
                    )
                }
            }
        }

        // Wellness Score
        item {
            WellnessScoreCard()
        }

        // Section title
        item {
            Text(
                text = "Today's Health",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = DarkText
            )
        }

        // Health parameter cards
        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(260.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                userScrollEnabled = false
            ) {

                items(
                    listOf(
                        HealthData(
                            "Sleep",
                            "6.5 hrs",
                            Icons.Filled.Bedtime,
                            Color(0xFF6C63B5)
                        ),
                        HealthData(
                            "Steps",
                            "5,240",
                            Icons.Filled.DirectionsWalk,
                            Green
                        ),
                        HealthData(
                            "Water",
                            "1.8 L",
                            Icons.Filled.WaterDrop,
                            Color(0xFF2196F3)
                        ),
                        HealthData(
                            "Screen Time",
                            "6.8 hrs",
                            Icons.Filled.PhoneAndroid,
                            Color(0xFFE68A35)
                        )
                    )
                ) { data ->

                    HealthCard(data)
                }
            }
        }

        // Today's insight
        item {
            InsightCard(
                title = "Today's Insight",
                message = "Your sleep is below your usual baseline. Try getting some extra rest tonight.",
                icon = Icons.Filled.Lightbulb
            )
        }

        // One small change
        item {
            InsightCard(
                title = "One Small Change",
                message = "Try keeping your phone away 30 minutes before bedtime.",
                icon = Icons.Filled.TipsAndUpdates
            )
        }

        item {
            Text(
                text = "Small steps, healthier you 💚",
                color = Green,
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp)
            )
        }
    }
}

// ---------------- WELLNESS SCORE ----------------

@Composable
fun WellnessScoreCard() {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = Green
        )
    ) {

        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "YOUR WELLNESS SCORE",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 14.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "78",
                    color = Color.White,
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = " / 100",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                progress = { 0.78f },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(8.dp),
                color = Color.White,
                trackColor = Color.White.copy(alpha = 0.25f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "You're doing great! Keep it up.",
                color = Color.White,
                fontSize = 14.sp
            )
        }
    }
}

// ---------------- HEALTH CARDS ----------------

data class HealthData(
    val title: String,
    val value: String,
    val icon: ImageVector,
    val color: Color
)

@Composable
fun HealthCard(data: HealthData) {

    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Icon(
                imageVector = data.icon,
                contentDescription = data.title,
                tint = data.color,
                modifier = Modifier.size(30.dp)
            )

            Column {

                Text(
                    text = data.title,
                    fontSize = 14.sp,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(4.dp))

                Text(
                    text = data.value,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )
            }
        }
    }
}

// ---------------- INSIGHT CARDS ----------------

@Composable
fun InsightCard(
    title: String,
    message: String,
    icon: ImageVector
) {

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = LightGreen
        )
    ) {

        Row(
            modifier = Modifier.padding(18.dp),
            verticalAlignment = Alignment.Top
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = Green,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(14.dp))

            Column {

                Text(
                    text = title,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkText
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = message,
                    fontSize = 14.sp,
                    color = DarkText,
                    lineHeight = 21.sp
                )
            }
        }
    }
}

// ---------------- OTHER SCREENS ----------------

@Composable
fun PlaceholderScreen(
    title: String,
    message: String,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Background)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = title,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = Green
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = message,
            fontSize = 16.sp,
            color = Color.Gray
        )
    }
}