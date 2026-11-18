
package com.healthvista.app.repository


import com.healthvista.app.repository.HealthRecordValidator
import com.healthvista.app.data.HealthDatabaseHelper
import com.healthvista.app.model.HealthRecord

class HealthRepository(
    private val database: HealthDatabaseHelper
) {

    fun saveHealthRecord(
        record: HealthRecord
    ): Result<Long> {

        val errors = HealthRecordValidator.validate(record)

        if (errors.isNotEmpty()) {
            return Result.failure(
                IllegalArgumentException(errors.joinToString())
            )
        }

        return try {

            val result = database.insertRecord(record)

            if (result == -1L) {
                Result.failure(
                    IllegalStateException("Could not save health record")
                )
            } else {
                Result.success(result)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun getHealthRecords(
        userId: String
    ): List<HealthRecord> {

        return database.getAllRecords(userId)
    }

    fun getHealthRecordByDate(
        userId: String,
        date: String
    ): HealthRecord? {

        return database.getRecordByDate(userId, date)
    }

    fun updateHealthRecord(
        record: HealthRecord
    ): Result<Int> {

        val errors = HealthRecordValidator.validate(record)

        if (errors.isNotEmpty()) {
            return Result.failure(
                IllegalArgumentException(errors.joinToString())
            )
        }

        return try {

            val result = database.updateRecord(record)

            if (result == 0) {
                Result.failure(
                    IllegalStateException("Record not found")
                )
            } else {
                Result.success(result)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun deleteHealthRecord(
        recordId: Int,
        userId: String
    ): Result<Int> {

        return try {

            val result = database.deleteRecord(recordId, userId)

            if (result == 0) {
                Result.failure(
                    IllegalStateException("Record not found")
                )
            } else {
                Result.success(result)
            }

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
