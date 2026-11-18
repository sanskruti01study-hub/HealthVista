

package com.healthvista.app.ai

import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL

object AiApiClient {

    private const val BASE_URL =
        "http://10.0.2.2:5000/analyze"

    fun analyze(request: JSONObject): JSONObject {

        val connection =
            URL(BASE_URL).openConnection() as HttpURLConnection

        try {

            connection.requestMethod = "POST"
            connection.connectTimeout = 10000
            connection.readTimeout = 20000

            connection.doOutput = true

            connection.setRequestProperty(
                "Content-Type",
                "application/json"
            )

            connection.setRequestProperty(
                "Accept",
                "application/json"
            )

            connection.outputStream.use { output ->
                output.write(
                    request.toString().toByteArray(Charsets.UTF_8)
                )
            }

            val responseCode = connection.responseCode

            val responseStream =
                if (responseCode in 200..299) {
                    connection.inputStream
                } else {
                    connection.errorStream
                }

            val responseText =
                responseStream.bufferedReader().use {
                    it.readText()
                }

            if (responseCode !in 200..299) {
                throw Exception(
                    "AI server error $responseCode: $responseText"
                )
            }

            return JSONObject(responseText)

        } finally {
            connection.disconnect()
        }
    }
}
