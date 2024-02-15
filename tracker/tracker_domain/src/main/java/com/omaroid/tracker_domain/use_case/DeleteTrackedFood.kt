package com.omaroid.tracker_domain.use_case

import com.omaroid.tracker_domain.model.TrackedFood
import com.omaroid.tracker_domain.repository.TrackerRepository

class DeleteTrackedFood(
    private val repository: TrackerRepository
) {
    suspend operator fun invoke(
        trackedFood: TrackedFood
    ) {
        repository.deleteTackedFood(trackedFood)
    }
}