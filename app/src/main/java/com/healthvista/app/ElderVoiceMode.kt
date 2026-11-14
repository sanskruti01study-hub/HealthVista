package com.healthvista.app

object ElderVoiceMode {

    fun getMessage(text: String): String {
        val command = text.lowercase()

        return when {
            "water" in command ->
                "It is time to drink some water."

            "medicine" in command ->
                "Please remember your scheduled medicine."

            "exercise" in command ->
                "It is time for a little movement."

            "sleep" in command ->
                "It is time to rest and sleep."

            "break" in command ->
                "Please take a short break."

            else ->
                "Welcome to Health Vista. How can I help you?"
        }
    }

    fun getTextSize(): Int = 22
}