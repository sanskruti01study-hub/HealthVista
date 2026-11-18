
package com.healthvista.app

import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import android.content.Context
import com.healthvista.app.data.HealthDatabaseHelper
import com.healthvista.app.model.HealthRecord
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HealthDatabaseTest {

    @Test
    fun insertAndRetrieveRecord() {

        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val helper = HealthDatabaseHelper(context)

        try {
            val record = HealthRecord(
                userId = "test_user",
                recordDate = "2026-11-15",
                screenTimeMinutes = 120,
                steps = 5000
            )

            val id = helper.insertRecord(record)

            assertNotEquals(-1L, id)

            val saved = helper.getRecordByDate(
                "test_user",
                "2026-11-15"
            )

            assertEquals(5000, saved?.steps)
            assertEquals(120, saved?.screenTimeMinutes)

        } finally {
            helper.close()
            context.deleteDatabase("healthvista.db")
        }
    }

    @Test
    fun deleteRecordWorks() {

        val context = ApplicationProvider
            .getApplicationContext<Context>()

        val helper = HealthDatabaseHelper(context)

        try {
            val record = HealthRecord(
                userId = "test_user",
                recordDate = "2026-11-14",
                steps = 4000
            )

            val id = helper.insertRecord(record).toInt()

            helper.deleteRecord(id, "test_user")

            val saved = helper.getRecordByDate(
                "test_user",
                "2026-11-14"
            )

            assertNull(saved)

        } finally {
            helper.close()
            context.deleteDatabase("healthvista.db")
        }
    }
}
