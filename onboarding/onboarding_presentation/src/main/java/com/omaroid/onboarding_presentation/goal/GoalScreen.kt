package com.omaroid.onboarding_presentation.goal

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.omaroid.core.R
import com.omaroid.core.domain.model.ActivityLevel
import com.omaroid.core.domain.model.GoalType
import com.omaroid.core.util.UiEvent
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.onboarding_presentation.activity.ActivityViewModel
import com.omaroid.onboarding_presentation.components.ActionButton
import com.omaroid.onboarding_presentation.components.SelectableButton

@Composable
fun GoalScreen(
    onNextClick: () -> Unit,
    viewModel: GoalViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }

    GoalScreenContent(
        onGoalClick = {
            viewModel.onGoalTypeSelect(it)
        },
        goalType = viewModel.selectedGoal,
        onClick = { viewModel.onNextClick() }
    )
}

@Composable
fun GoalScreenContent(
    modifier: Modifier = Modifier,
    onGoalClick: (GoalType) -> Unit,
    goalType: GoalType,
    onClick: () -> Unit
) {
    val spacing = LocalSpacing.current
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.spaceLarge)
    ) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.whats_your_activity_level),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    text = stringResource(id = R.string.lose),
                    isSelected = goalType is GoalType.LoseWeight,
                    color = MaterialTheme.colorScheme.primary,
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        onGoalClick(GoalType.LoseWeight)
                    },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.keep),
                    isSelected = goalType is GoalType.KeepWeight,
                    color = MaterialTheme.colorScheme.primary,
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        onGoalClick(GoalType.KeepWeight)
                    },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.gain),
                    isSelected = goalType is GoalType.GainWeight,
                    color = MaterialTheme.colorScheme.primary,
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        onGoalClick(GoalType.GainWeight)
                    },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
            }
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
private fun ActivityScreenPreview() {
    GoalScreenContent(onGoalClick = {}, goalType = GoalType.GainWeight) {}
}