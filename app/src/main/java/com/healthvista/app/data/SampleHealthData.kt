
package com.healthvista.app.data

import com.healthvista.app.model.HealthRecord

object SampleHealthData {

    fun getSampleRecords(): List<HealthRecord> {

        val userId = "demo_user"

        return listOf(

            HealthRecord(
                userId = userId,
                recordDate = "2026-11-09",
                screenTimeMinutes = 180,
                physicalActivityMinutes = 45,
                waterIntakeMl = 2000,
                sleepDurationMinutes = 480,
                steps = 6500,
                sweetIntakeServings = 2,
                existingHealthIssues = null
            ),

            HealthRecord(
                userId = userId,
                recordDate = "2026-11-10",
                screenTimeMinutes = 210,
                physicalActivityMinutes = 30,
                waterIntakeMl = 1800,
                sleepDurationMinutes = 450,
                steps = 5200,
                sweetIntakeServings = 1,
                existingHealthIssues = null
            ),

            HealthRecord(
                userId = userId,
                recordDate = "2026-11-11",
                screenTimeMinutes = 160,
                physicalActivityMinutes = 60,
                waterIntakeMl = 2200,
                sleepDurationMinutes = 480,
                steps = 7500,
                sweetIntakeServings = 2,
                existingHealthIssues = null
            ),

            HealthRecord(
                userId = userId,
                recordDate = "2026-11-12",
                screenTimeMinutes = 240,
                physicalActivityMinutes = 25,
                waterIntakeMl = 1700,
                sleepDurationMinutes = 420,
                steps = 4000,
                sweetIntakeServings = 3,
                existingHealthIssues = null
            ),

            HealthRecord(
                userId = userId,
                recordDate = "2026-11-13",
                screenTimeMinutes = 150,
                physicalActivityMinutes = 50,
                waterIntakeMl = 2300,
                sleepDurationMinutes = 480,
                steps = 8000,
                sweetIntakeServings = 1,
                existingHealthIssues = null
            ),

            HealthRecord(
                userId = userId,
                recordDate = "2026-11-14",
                screenTimeMinutes = 200,
                physicalActivityMinutes = 40,
                waterIntakeMl = 2100,
                sleepDurationMinutes = 450,
                steps = 6000,
                sweetIntakeServings = 2,
                existingHealthIssues = null
            ),

            HealthRecord(
                userId = userId,
                recordDate = "2026-11-15",
                screenTimeMinutes = 170,
                physicalActivityMinutes = 55,
                waterIntakeMl = 2400,
                sleepDurationMinutes = 480,
                steps = 7200,
                sweetIntakeServings = 1,
                existingHealthIssues = null
            )
        )
    }
}
