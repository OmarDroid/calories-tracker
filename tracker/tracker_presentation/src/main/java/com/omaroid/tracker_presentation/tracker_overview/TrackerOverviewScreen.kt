package com.omaroid.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.tracker_presentation.tracker_overview.components.DaySelector
import com.omaroid.tracker_presentation.tracker_overview.components.NutrientsHeader

@Composable
fun TrackerOverviewScreen(
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val spacing = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = spacing.spaceMedium)
    ) {
        item {
            NutrientsHeader(state = state)
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            DaySelector(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = spacing.spaceMedium),
                date = state.date,
                onNextClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
                },
                onPreviousClick = {
                    viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                },
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))

        }
    }
}

@Preview
@Composable
private fun TrackerOverviewScreenPreview() {
    TrackerOverviewScreen()
}
