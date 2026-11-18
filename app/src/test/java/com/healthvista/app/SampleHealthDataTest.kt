
package com.healthvista.app

import com.healthvista.app.data.SampleHealthData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class SampleHealthDataTest {

    @Test
    fun sampleDataContainsSevenRecords() {

        val records = SampleHealthData.getSampleRecords()

        assertEquals(7, records.size)
    }

    @Test
    fun allSampleRecordsHaveValidValues() {

        val records = SampleHealthData.getSampleRecords()

        assertTrue(
            records.all {
                it.screenTimeMinutes == null ||
                        it.screenTimeMinutes >= 0
            }
        )

        assertTrue(
            records.all {
                it.steps == null || it.steps >= 0
            }
        )
    }
}
