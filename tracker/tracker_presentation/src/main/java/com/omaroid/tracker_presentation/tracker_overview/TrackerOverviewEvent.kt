package com.omaroid.tracker_presentation.tracker_overview

import com.omaroid.tracker_domain.model.TrackedFood

sealed class TrackerOverviewEvent {
    data object OnNextDayClick: TrackerOverviewEvent()
    data object OnPreviousDayClick: TrackerOverviewEvent()
    data class OnToggleMealClick(val meal: Meal): TrackerOverviewEvent()
    data class OnDeleteTrackerFood(val trackedFood: TrackedFood): TrackerOverviewEvent()
}