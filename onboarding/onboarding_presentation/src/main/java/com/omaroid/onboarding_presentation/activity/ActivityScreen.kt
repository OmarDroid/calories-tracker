package com.omaroid.onboarding_presentation.activity

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
import com.omaroid.core.util.UiEvent
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.onboarding_presentation.components.ActionButton
import com.omaroid.onboarding_presentation.components.SelectableButton

@Composable
fun ActivityScreen(
    onNextClick: () -> Unit,
    viewModel: ActivityViewModel = hiltViewModel()
) {
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }
        }
    }

    ActivityScreenContent(
        onActivityClick = {
            viewModel.onActivityClick(it)
        },
        activityLevel = viewModel.selectedActivityLevel,
        onClick = { viewModel.onNextClick() }
    )
}

@Composable
fun ActivityScreenContent(
    modifier: Modifier = Modifier,
    onActivityClick: (ActivityLevel) -> Unit,
    activityLevel: ActivityLevel,
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
                    text = stringResource(id = R.string.low),
                    isSelected = activityLevel is ActivityLevel.Low,
                    color = MaterialTheme.colorScheme.primary,
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        onActivityClick(ActivityLevel.Low)
                    },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.medium),
                    isSelected = activityLevel is ActivityLevel.Medium,
                    color = MaterialTheme.colorScheme.primary,
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        onActivityClick(ActivityLevel.Medium)
                    },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = modifier.width(spacing.spaceMedium))
                SelectableButton(
                    text = stringResource(id = R.string.high),
                    isSelected = activityLevel is ActivityLevel.High,
                    color = MaterialTheme.colorScheme.primary,
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = {
                        onActivityClick(ActivityLevel.High)
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
    ActivityScreenContent(onActivityClick = {}, activityLevel = ActivityLevel.Medium){}
}