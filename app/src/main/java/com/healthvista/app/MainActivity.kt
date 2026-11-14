package com.healthvista.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.healthvista.app.screens.HomeScreen
import com.healthvista.app.screens.ProgressScreen
import com.healthvista.app.screens.AICoachScreen
import com.healthvista.app.screens.RemindersScreen
import com.healthvista.app.screens.ProfileScreen
import com.healthvista.app.ui.theme.HealthVistaTheme

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

    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf(
        "Home",
        "Progress",
        "AI Coach",
        "Reminders",
        "Profile"
    )

    Scaffold(
        bottomBar = {
            NavigationBar {

                tabs.forEachIndexed { index, title ->

                    NavigationBarItem(
                        selected = selectedTab == index,
                        onClick = { selectedTab = index },
                        icon = {
                            Text(
                                text = when (index) {
                                    0 -> "🏠"
                                    1 -> "📈"
                                    2 -> "🤖"
                                    3 -> "🔔"
                                    else -> "👤"
                                }
                            )
                        },
                        label = { Text(title) }
                    )
                }
            }
        }
    ) { padding ->

        androidx.compose.foundation.layout.Box(
            modifier = Modifier.padding(padding)
        ) {

            when (selectedTab) {

                0 -> HomeScreen()

                1 -> ProgressScreen()

                2 -> AICoachScreen()

                3 -> RemindersScreen()

                4 -> ProfileScreen()
            }
        }
    }
}