package com.omaroid.tracker_domain.repository

import com.omaroid.tracker_domain.model.TrackableFood
import com.omaroid.tracker_domain.model.TrackedFood
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

interface TrackerRepository {
    suspend fun searchFood(
        query: String,
        page: Int,
        pageSize: Int,
    ): Result<List<TrackableFood>>

    suspend fun insertTackedFood(food: TrackedFood)
    suspend fun deleteTackedFood(food: TrackedFood)
    suspend fun getFoodsForDate(localDate: LocalDate): Flow<List<TrackedFood>>
}