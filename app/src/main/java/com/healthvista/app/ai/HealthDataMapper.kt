

package com.healthvista.app.ai

import com.healthvista.app.model.HealthRecord
import org.json.JSONArray
import org.json.JSONObject

object HealthDataMapper {

    fun createRequest(
        today: HealthRecord,
        history: List<HealthRecord>
    ): JSONObject {

        val todayJson = convertRecord(today)
        val historyJson = JSONArray()

        history.forEach { record ->
            historyJson.put(convertRecord(record))
        }

        return JSONObject().apply {
            put("today", todayJson)
            put("history", historyJson)
        }
    }

    private fun convertRecord(
        record: HealthRecord
    ): JSONObject {

        return JSONObject().apply {

            put("steps", record.steps ?: JSONObject.NULL)

            put(
                "sleep_hours",
                record.sleepDurationMinutes?.let {
                    it / 60.0
                } ?: JSONObject.NULL
            )

            put(
                "screen_time_hours",
                record.screenTimeMinutes?.let {
                    it / 60.0
                } ?: JSONObject.NULL
            )

            put(
                "water_intake_liters",
                record.waterIntakeMl?.let {
                    it / 1000.0
                } ?: JSONObject.NULL
            )

            put(
                "sweet_intake_servings",
                record.sweetIntakeServings ?: JSONObject.NULL
            )

            put(
                "physical_activity_minutes",
                record.physicalActivityMinutes ?: JSONObject.NULL
            )

            put(
                "existing_health_issues",
                record.existingHealthIssues ?: JSONObject.NULL
            )
        }
    }
}
