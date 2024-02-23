package com.omaroid.onboarding_presentation.nutrient_goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omaroid.core.util.UiEvent
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.core.R
import com.omaroid.onboarding_presentation.components.ActionButton
import com.omaroid.onboarding_presentation.components.UnitTextField

@Composable
fun NutrientGoalScreen(
    snackbarHostState: SnackbarHostState,
    onNextClick: () -> Unit,
    viewModel: NutrientGoalViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    LaunchedEffect(true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                is UiEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = event.message.asString(context)
                    )
                }

                else -> Unit
            }

        }
    }

    NutrientGoalScreenContent(
        carbRatio = viewModel.state.carbsRatio,
        proteinRatio = viewModel.state.proteinRatio,
        fatRatio = viewModel.state.fatRatio,
        onCarbRatioChanged = { viewModel.onEvent(NutrientGoalEvent.OnCarbRatioEnter(it)) },
        onProteinRatioChanged = { viewModel.onEvent(NutrientGoalEvent.OnProteinRatioEnter(it)) },
        onFatRatioChanged = { viewModel.onEvent(NutrientGoalEvent.OnFatRatioEnter(it)) },
        onClick = { viewModel.onEvent(NutrientGoalEvent.OnNextClick) }
    )

}

@Composable
fun NutrientGoalScreenContent(
    carbRatio: String,
    proteinRatio: String,
    fatRatio: String,
    onCarbRatioChanged: (String) -> Unit,
    onProteinRatioChanged: (String) -> Unit,
    onFatRatioChanged: (String) -> Unit,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.what_are_your_nutrient_goals),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = carbRatio,
                onValueChanged = onCarbRatioChanged,
                unit = stringResource(id = R.string.percent_carbs),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary, fontSize = 70.sp
                )
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = proteinRatio,
                onValueChanged = onProteinRatioChanged,
                unit = stringResource(id = R.string.percent_proteins),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary, fontSize = 70.sp
                )
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = fatRatio,
                onValueChanged = onFatRatioChanged,
                unit = stringResource(id = R.string.percent_fats),
                textStyle = TextStyle(
                    color = MaterialTheme.colorScheme.primary, fontSize = 70.sp
                )
            )
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onClick,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NutrientScreenPreview(modifier: Modifier = Modifier) {
    NutrientGoalScreenContent(
        carbRatio = "30",
        proteinRatio = "30",
        fatRatio = "40",
        onCarbRatioChanged = {},
        onProteinRatioChanged = {},
        onFatRatioChanged = {},
        onClick = {}
    )
}