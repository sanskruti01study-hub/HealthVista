
package com.healthvista.app.repository

import com.healthvista.app.model.HealthRecord

object HealthRecordValidator {

    fun validate(record: HealthRecord): List<String> {

        val errors = mutableListOf<String>()

        if (record.userId.isBlank()) {
            errors.add("User ID is required")
        }

        if (record.recordDate.isBlank()) {
            errors.add("Record date is required")
        }

        if (record.screenTimeMinutes != null &&
            record.screenTimeMinutes !in 0..1440) {
            errors.add("Invalid screen time")
        }

        if (record.physicalActivityMinutes != null &&
            record.physicalActivityMinutes !in 0..1440) {
            errors.add("Invalid physical activity")
        }

        if (record.waterIntakeMl != null &&
            record.waterIntakeMl < 0) {
            errors.add("Invalid water intake")
        }

        if (record.sleepDurationMinutes != null &&
            record.sleepDurationMinutes !in 0..1440) {
            errors.add("Invalid sleep duration")
        }

        if (record.steps != null && record.steps < 0) {
            errors.add("Invalid step count")
        }

        if (record.sweetIntakeServings != null &&
            record.sweetIntakeServings < 0) {
            errors.add("Invalid sweet intake")
        }

        if ((record.existingHealthIssues?.length ?: 0) > 500) {
            errors.add("Health information is too long")
        }

        return errors
    }
}
