
package com.healthvista.app.data

object DataValidation {

    fun validate(record: HealthRecord): List<String> {

        val errors = mutableListOf<String>()

        if (record.userId.isBlank()) {
            errors.add("User ID is required")
        }

        if (record.date.isBlank()) {
            errors.add("Date is required")
        }

        if (record.screenTimeHours !in 0.0..24.0) {
            errors.add("Screen time must be between 0 and 24 hours")
        }

        if (record.dailyActivity !in listOf("Low", "Medium", "High")) {
            errors.add("Activity must be Low, Medium or High")
        }

        if (record.waterIntakeLitres !in 0.0..20.0) {
            errors.add("Water intake must be between 0 and 20 litres")
        }

        if (record.sleepHours !in 0.0..24.0) {
            errors.add("Sleep must be between 0 and 24 hours")
        }

        if (record.stepCount !in 0..200000) {
            errors.add("Invalid step count")
        }

        if (record.sweetIntake !in listOf("Low", "Medium", "High")) {
            errors.add("Sweet intake must be Low, Medium or High")
        }

        return errors
    }
}
