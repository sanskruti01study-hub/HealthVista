
package com.healthvista.app.model

data class HealthRecord(

    val id: Int = 0,

    val userId: String,

    val recordDate: String,

    val screenTimeMinutes: Int? = null,

    val physicalActivityMinutes: Int? = null,

    val waterIntakeMl: Int? = null,

    val sleepDurationMinutes: Int? = null,

    val steps: Int? = null,

    val sweetIntakeServings: Int? = null,

    val existingHealthIssues: String? = null
)
