package com.healthvista.app

object VoiceCommandHandler {

    fun getReply(command: String): String {

        val text = command.lowercase()

        return when {
            text.contains("hello") || text.contains("hi") ->
                "Hello! Welcome to Health Vista."

            text.contains("health tip") ->
                "Remember to drink enough water and take regular breaks."

            text.contains("water") ->
                "Don't forget to stay hydrated."

            text.contains("reminder") ->
                "Your health reminders help you maintain a healthy routine."

            text.contains("who are you") ->
                "I am your Health Vista voice assistant."

            text.contains("thank") ->
                "You're welcome! Stay healthy."

            else ->
                "Sorry, I didn't understand. Please try again."
        }
    }
}
