package com.omaroid.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omaroid.core.R
import com.omaroid.core.util.UiText
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.tracker_domain.model.MealType
import com.omaroid.tracker_presentation.tracker_overview.components.DaySelector
import com.omaroid.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.omaroid.tracker_presentation.tracker_overview.components.NutrientsHeader
import java.time.LocalDate

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

        items(state.meals) {meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))},
                content = {},
                modifier = Modifier.fillMaxSize()
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TrackerOverviewScreenPreview() {
    val defaultMeals = listOf(
        Meal(
            name = UiText.StringResources(R.string.breakfast),
            drawableRes = R.drawable.ic_breakfast,
            mealType = MealType.Breakfast
        ),
        Meal(
            name = UiText.StringResources(R.string.lunch),
            drawableRes = R.drawable.ic_lunch,
            mealType = MealType.Lunch
        ),
        Meal(
            name = UiText.StringResources(R.string.dinner),
            drawableRes = R.drawable.ic_dinner,
            mealType = MealType.Dinner
        ),
        Meal(
            name = UiText.StringResources(R.string.snacks),
            drawableRes = R.drawable.ic_snack,
            mealType = MealType.Snack
        ),
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp)
    ) {
        item {
            NutrientsHeader(
                state = TrackerOverviewState(
                    totalCarbs = 20,
                    totalProtein = 20,
                    totalFat = 20,
                    totalCalories = 40,
                    carbsGoal = 30,
                    proteinGoal = 30,
                    fatGoal = 40,
                    caloriesGoal = 60,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            DaySelector(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                date = LocalDate.now(),
                onNextClick = {
                },
                onPreviousClick = {
                },
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        items(defaultMeals) { meal ->
            ExpandableMeal(
                meal = meal,
                onToggleClick = {},
                content = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}
