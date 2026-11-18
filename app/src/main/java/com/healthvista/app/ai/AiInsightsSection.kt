
package com.healthvista.app.ai

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.healthvista.app.data.HealthDatabaseHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

@Composable
fun AiInsightsSection(
    database: HealthDatabaseHelper,
    userId: String
) {

    var loading by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf<JSONObject?>(null) }
    var error by remember { mutableStateOf<String?>(null) }

    var requestNumber by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {

        Text(
            text = "AI Wellness Insights",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Get general lifestyle insights from your saved health records."
        )

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {
                requestNumber++
            },
            enabled = !loading,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                if (loading) "Analysing..." else "Generate AI Insights"
            )
        }

        LaunchedEffect(requestNumber) {

            if (requestNumber == 0) return@LaunchedEffect

            loading = true
            error = null
            result = null

            try {

                val response = withContext(Dispatchers.IO) {

                    val records = database.getAllRecords(userId)

                    if (records.isEmpty()) {
                        throw Exception(
                            "No saved health records found. Save a health record first."
                        )
                    }

                    // Database returns newest record first.
                    val today = records.first()
                    val history = records.drop(1)

                    val request = HealthDataMapper.createRequest(
                        today = today,
                        history = history
                    )

                    AiApiClient.analyze(request)
                }

                result = response

            } catch (exception: Exception) {

                error = exception.message
                    ?: "Something went wrong."

            } finally {

                loading = false
            }
        }

        if (loading) {
            Spacer(modifier = Modifier.height(12.dp))

            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        error?.let {
            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = it,
                color = MaterialTheme.colorScheme.error
            )
        }

        result?.let { response ->

            Spacer(modifier = Modifier.height(16.dp))

            Card(
                modifier = Modifier.fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier.padding(16.dp)
                ) {

                    Text(
                        text = "AI Analysis Result",
                        style = MaterialTheme.typography.titleLarge
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = response.toString(2)
                    )
                }
            }
        }
    }
}
