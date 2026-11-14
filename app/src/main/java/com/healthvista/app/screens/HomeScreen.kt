package com.healthvista.app.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen() {

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(vertical = 20.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        item {
            Text(
                text = "HEALTH VISTA",
                style = MaterialTheme.typography.headlineMedium
            )

            Text("Your Personal Wellness Assistant")
        }

        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text("Wellness Score")

                    Text(
                        "78 / 100",
                        style = MaterialTheme.typography.headlineLarge
                    )

                    Text("Good progress! Keep it up.")
                }
            }
        }

        item {
            Text(
                "Today's Health",
                style = MaterialTheme.typography.titleLarge
            )
        }

        item {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.height(220.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item { HealthCard("Sleep", "6.5 hrs") }
                item { HealthCard("Steps", "5,240") }
                item { HealthCard("Water", "1.8 L") }
                item { HealthCard("Screen Time", "6.8 hrs") }
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "Today's Insight",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Your sleep is slightly below the recommended level.")
                }
            }
        }

        item {
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "One Small Change",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text("Try sleeping 30 minutes earlier tonight.")
                }
            }
        }
    }
}

@Composable
fun HealthCard(title: String, value: String) {
    Card(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(14.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(title)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                value,
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}