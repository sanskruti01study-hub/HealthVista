
package com.healthvista.app.data

data class UserProfile(
    val userId: String,
    val name: String,
    val age: Int,
    val healthIssues: String? = null,

    // User preferences
    val voiceEnabled: Boolean = true,
    val elderMode: Boolean = false,
    val notificationsEnabled: Boolean = true
)
