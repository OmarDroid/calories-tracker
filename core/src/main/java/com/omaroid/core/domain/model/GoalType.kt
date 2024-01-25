package com.omaroid.core.domain.model

sealed class GoalType(val goalType: String) {
    data object LoseWeight: GoalType("lose_weight")
    data object GainWeight: GoalType("gain_weight")
    data object KeepWeight: GoalType("keep_weight")

    companion object {
        fun fromString(goalType: String): GoalType {
            return when(goalType) {
                "loose_weight" -> LoseWeight
                "gain_weight" -> GainWeight
                "keep_weight" -> KeepWeight
                else -> throw IllegalStateException("Unknown GoalType")
            }
        }
    }
}