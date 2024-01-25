package com.omaroid.core.domain.model

sealed class Gender(val gender: String) {
    data object Male: Gender("male")
    data object Female: Gender("female")

    companion object {
        fun fromString(gender: String): Gender {
            return when(gender) {
                "male" -> Male
                "female" -> Female
                else -> throw IllegalStateException("Unknown Gender")
            }
        }
    }
}