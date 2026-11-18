
package com.healthvista.app.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.healthvista.app.model.HealthRecord

class HealthDatabaseHelper(context: Context) :
    SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {

    companion object {
        private const val DATABASE_NAME = "healthvista.db"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "health_records"
    }

    override fun onCreate(db: SQLiteDatabase) {

        val createTable = """
            CREATE TABLE $TABLE_NAME (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                userId TEXT NOT NULL,
                recordDate TEXT NOT NULL,
                screenTimeMinutes INTEGER,
                physicalActivityMinutes INTEGER,
                waterIntakeMl INTEGER,
                sleepDurationMinutes INTEGER,
                steps INTEGER,
                sweetIntakeServings INTEGER,
                existingHealthIssues TEXT,
                UNIQUE(userId, recordDate)
            )
        """.trimIndent()

        db.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase,
        oldVersion: Int,
        newVersion: Int
    ) {
        // Add database migration logic when the schema changes.
    }

    fun insertRecord(record: HealthRecord): Long {

        val values = ContentValues().apply {
            put("userId", record.userId)
            put("recordDate", record.recordDate)

            putNullable("screenTimeMinutes", record.screenTimeMinutes)
            putNullable("physicalActivityMinutes", record.physicalActivityMinutes)
            putNullable("waterIntakeMl", record.waterIntakeMl)
            putNullable("sleepDurationMinutes", record.sleepDurationMinutes)
            putNullable("steps", record.steps)
            putNullable("sweetIntakeServings", record.sweetIntakeServings)

            put("existingHealthIssues", record.existingHealthIssues)
        }

        return writableDatabase.insertWithOnConflict(
            TABLE_NAME,
            null,
            values,
            SQLiteDatabase.CONFLICT_REPLACE
        )
    }

    private fun ContentValues.putNullable(
        key: String,
        value: Int?
    ) {
        if (value == null) {
            putNull(key)
        } else {
            put(key, value)
        }
    }

    fun getAllRecords(userId: String): List<HealthRecord> {

        val records = mutableListOf<HealthRecord>()

        val cursor = readableDatabase.query(
            TABLE_NAME,
            null,
            "userId = ?",
            arrayOf(userId),
            null,
            null,
            "recordDate DESC"
        )

        cursor.use {

            while (it.moveToNext()) {

                records.add(
                    HealthRecord(
                        id = it.getInt(it.getColumnIndexOrThrow("id")),
                        userId = it.getString(it.getColumnIndexOrThrow("userId")),
                        recordDate = it.getString(it.getColumnIndexOrThrow("recordDate")),

                        screenTimeMinutes = it.getNullableInt("screenTimeMinutes"),
                        physicalActivityMinutes = it.getNullableInt("physicalActivityMinutes"),
                        waterIntakeMl = it.getNullableInt("waterIntakeMl"),
                        sleepDurationMinutes = it.getNullableInt("sleepDurationMinutes"),
                        steps = it.getNullableInt("steps"),
                        sweetIntakeServings = it.getNullableInt("sweetIntakeServings"),

                        existingHealthIssues = it.getString(
                            it.getColumnIndexOrThrow("existingHealthIssues")
                        )
                    )
                )
            }
        }

        return records
    }

    private fun android.database.Cursor.getNullableInt(
        columnName: String
    ): Int? {

        val index = getColumnIndexOrThrow(columnName)

        return if (isNull(index)) null else getInt(index)
    }

    fun getRecordByDate(
        userId: String,
        date: String
    ): HealthRecord? {

        return getAllRecords(userId).firstOrNull {
            it.recordDate == date
        }
    }

    fun updateRecord(record: HealthRecord): Int {

        val values = ContentValues().apply {
            put("userId", record.userId)
            put("recordDate", record.recordDate)

            putNullable("screenTimeMinutes", record.screenTimeMinutes)
            putNullable("physicalActivityMinutes", record.physicalActivityMinutes)
            putNullable("waterIntakeMl", record.waterIntakeMl)
            putNullable("sleepDurationMinutes", record.sleepDurationMinutes)
            putNullable("steps", record.steps)
            putNullable("sweetIntakeServings", record.sweetIntakeServings)

            put("existingHealthIssues", record.existingHealthIssues)
        }

        return writableDatabase.update(
            TABLE_NAME,
            values,
            "id = ? AND userId = ?",
            arrayOf(record.id.toString(), record.userId)
        )
    }

    fun deleteRecord(
        recordId: Int,
        userId: String
    ): Int {

        return writableDatabase.delete(
            TABLE_NAME,
            "id = ? AND userId = ?",
            arrayOf(recordId.toString(), userId)
        )
    }
}
