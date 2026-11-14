
package com.healthvista.app.data

data class HealthRecord(
    val userId: String,
    val date: String,

    // Seven health parameters
    val screenTimeHours: Double,
    val dailyActivity: String,
    val waterIntakeLitres: Double,
    val sleepHours: Double,
    val stepCount: Int,
    val sweetIntake: String,
    val existingHealthIssues: String? = null
)
