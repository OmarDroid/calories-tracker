package com.omaroid.core.domain.model

sealed class ActivityLevel(val activityLevel: String) {
    data object Low: ActivityLevel("low")
    data object Medium: ActivityLevel("medium")
    data object High: ActivityLevel("high")

    companion object {
        fun fromString(activityLevel: String): ActivityLevel {
            return when(activityLevel) {
                "low" -> Low
                "medium" -> Medium
                "high" -> High
                else -> throw IllegalStateException("Unknown ActivityLevel")
            }
        }
    }
}