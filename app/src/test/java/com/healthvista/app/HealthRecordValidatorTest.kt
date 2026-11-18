
package com.healthvista.app

import com.healthvista.app.model.HealthRecord
import com.healthvista.app.repository.HealthRecordValidator
import org.junit.Assert.assertTrue
import org.junit.Test

class HealthRecordValidatorTest {

    @Test
    fun validRecordReturnsNoErrors() {

        val record = HealthRecord(
            userId = "demo_user",
            recordDate = "2026-11-15",
            screenTimeMinutes = 180,
            physicalActivityMinutes = 45,
            waterIntakeMl = 2000,
            sleepDurationMinutes = 480,
            steps = 6500,
            sweetIntakeServings = 2,
            existingHealthIssues = null
        )

        val errors = HealthRecordValidator.validate(record)

        assertTrue(errors.isEmpty())
    }

    @Test
    fun negativeStepsReturnsError() {

        val record = HealthRecord(
            userId = "demo_user",
            recordDate = "2026-11-15",
            steps = -10
        )

        val errors = HealthRecordValidator.validate(record)

        assertTrue(errors.contains("Invalid step count"))
    }

    @Test
    fun missingUserIdReturnsError() {

        val record = HealthRecord(
            userId = "",
            recordDate = "2026-11-15"
        )

        val errors = HealthRecordValidator.validate(record)

        assertTrue(errors.contains("User ID is required"))
    }
}
