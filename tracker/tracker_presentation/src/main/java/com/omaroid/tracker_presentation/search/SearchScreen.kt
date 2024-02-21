package com.omaroid.tracker_presentation.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import com.omaroid.core.R
import com.omaroid.core.util.UiEvent
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.tracker_domain.model.MealType
import com.omaroid.tracker_domain.model.TrackableFood
import com.omaroid.tracker_presentation.search.components.SearchTextField
import com.omaroid.tracker_presentation.search.components.TrackableFoodItem
import java.time.LocalDate

@ExperimentalComposeUiApi
@Composable
fun SearchScreen(
    snackbarHostState: SnackbarHostState,
    mealName: String,
    dayOfMonth: Int,
    month: Int,
    year: Int,
    onNavigateUp: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {

    val state = viewModel.state
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    LaunchedEffect(key1 = keyboardController) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                    keyboardController?.hide()
                }

                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    SearchScreenContent(
        mealName = mealName,
        query = state.query,
        onValueChange = { viewModel.onEvent(SearchEvent.OnQueryChange(it)) },
        shouldShowHint = state.isHintVisible,
        onSearch = {
            keyboardController?.hide()
            viewModel.onEvent(SearchEvent.OnSearch)
        },
        onFocusChanged = {
            viewModel.onEvent(SearchEvent.OnSearchFocusChange(it.isFocused))
        },
        trackableFood = state.trackableFood,
        onClick = {
            viewModel.onEvent(SearchEvent.OnToggleTrackableFood(it))
        },
        onAmountChange = { trackableFood: TrackableFood, amount: String ->
            viewModel.onEvent(
                SearchEvent.OnAmountForFoodChange(
                    trackableFood, amount
                )
            )
        },
        onTrack = {
            keyboardController?.hide()
            viewModel.onEvent(
                SearchEvent.OnTrackFoodClick(
                    food = it,
                    mealType = MealType.fromString(mealName),
                    date = LocalDate.of(year, month, dayOfMonth)
                )
            )
        },
        isSearching = state.isSearching
    )

}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun SearchScreenContent(
    mealName: String,
    query: String,
    onValueChange: (String) -> Unit,
    shouldShowHint: Boolean,
    onSearch: () -> Unit,
    onFocusChanged: (FocusState) -> Unit,
    trackableFood: List<TrackableFoodUiState>,
    onClick: (TrackableFood) -> Unit,
    onAmountChange: (TrackableFood, String) -> Unit,
    onTrack: (TrackableFood) -> Unit,
    isSearching: Boolean,
) {

    val spacing = LocalSpacing.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {
        Text(
            text = stringResource(id = R.string.add_meal, mealName),
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        SearchTextField(
            text = query,
            onValueChange = {
                onValueChange(it)
            },
            shouldShowHint = shouldShowHint,
            onSearch = {
                onSearch()
            },
            onFocusChanged = {
                onFocusChanged(it)
            }
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.onSurfaceVariant)
        ) {
            items(trackableFood) { food ->
                TrackableFoodItem(
                    trackableFoodUiState = food,
                    onClick = {
                        onClick(food.food)
                    },
                    onAmountChange = {
                        onAmountChange(food.food, it)
                    },
                    onTrack = {
                        onTrack(food.food)
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when {
            isSearching -> CircularProgressIndicator()
            trackableFood.isEmpty() -> {
                Text(
                    text = stringResource(id = R.string.no_results),
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    SearchScreenContent(
        mealName = "Breakfast",
        query = "Rice",
        onValueChange = {},
        shouldShowHint = false,
        onSearch = {},
        onFocusChanged = {},
        trackableFood = emptyList(),
        onClick = {},
        onAmountChange = { _, _ -> },
        onTrack = {},
        isSearching = true
    )
}