package com.healthvista.app

object BollywoodMemeMode {

    fun getReminderMessage(type: String): String {
        return when (type.lowercase()) {
            "water" -> "Picture abhi baaki hai! Drink water."
            "medicine" -> "Health first! Time for your medicine."
            "exercise" -> "Interval khatam! Time to move."
            "sleep" -> "The show must end! Time to rest."
            "break" -> "Even heroes need a break!"
            "checkup" -> "Don't miss your health checkup!"
            else -> "Stay healthy, stay happy!"
        }
    }
}

