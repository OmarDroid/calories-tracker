package com.omaroid.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.omaroid.core.R
import com.omaroid.core.util.UiText
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.tracker_domain.model.MealType
import com.omaroid.tracker_domain.model.TrackedFood
import com.omaroid.tracker_presentation.tracker_overview.Meal
import java.time.LocalDate

@Composable
fun TrackedFoodPerMeal(
    foods: List<TrackedFood>,
    meal: Meal,
    onNavigateToSearch: () -> Unit,
    onDeleteClick: (TrackedFood) -> Unit
) {
    val spacing = LocalSpacing.current
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = spacing.spaceSmall)
    ) {
        foods.forEach { food ->
            TrackedFoodItem(
                trackedFood = food,
                onDeleteClick = { onDeleteClick(food) }
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
        }
        AddButton(
            text = stringResource(
                id = R.string.add_meal,
                meal.name.asString(context)
            ),
            onClick = onNavigateToSearch,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
    }
}

@Preview(showBackground = true)
@Composable
private fun TrackedFoodPerMealPreview() {
    TrackedFoodPerMeal(
        foods = listOf(
            TrackedFood(
                name = "Rice",
                carbs = 12,
                protein = 30,
                fat = 41,
                imageUrl = null,
                mealType = MealType.Breakfast,
                amount = 23,
                date = LocalDate.now(),
                calories = 230
            )
        ),
        meal = Meal(
            name = UiText.StringResources(R.string.breakfast),
            drawableRes = R.drawable.ic_breakfast,
            mealType = MealType.Breakfast
        ),
        onDeleteClick = {},
        onNavigateToSearch = {}
    )
}
