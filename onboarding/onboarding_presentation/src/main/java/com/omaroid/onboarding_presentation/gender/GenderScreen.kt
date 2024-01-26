package com.omaroid.onboarding_presentation.gender

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
import com.omaroid.core.domain.model.Gender
import com.omaroid.core.util.UiEvent
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.onboarding_presentation.components.ActionButton
import com.omaroid.onboarding_presentation.components.SelectableButton

@Composable
fun GenderScreen(
    onNextClick: () -> Unit, viewModel: GenderViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.Success -> onNextClick()
                else -> Unit
            }

        }
    }
    GenderScreenContent(
        onGenderClick = {
            viewModel.onGenderClick(it)
        },
        gender = viewModel.selectedGender,
        onClick = { viewModel.onNextClick() }
    )
}

@Composable
fun GenderScreenContent(
    modifier: Modifier = Modifier,
    onGenderClick: (Gender) -> Unit,
    gender: Gender,
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
                text = stringResource(id = R.string.whats_your_gender),
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = modifier.height(spacing.spaceMedium))
            Row {
                SelectableButton(
                    modifier = modifier,
                    text = stringResource(id = R.string.male),
                    isSelected = gender is Gender.Male,
                    color = MaterialTheme.colorScheme.primary,
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { onGenderClick(Gender.Male) },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.width(spacing.spaceMedium))
                SelectableButton(
                    modifier = modifier,
                    text = stringResource(id = R.string.female),
                    isSelected = gender is Gender.Female,
                    color = MaterialTheme.colorScheme.primary,
                    selectedColor = MaterialTheme.colorScheme.onPrimary,
                    onClick = { onGenderClick(Gender.Female) },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
            }
        }
        ActionButton(
            text = stringResource(id = R.string.next),
            onClick = onClick,
            modifier = Modifier.align(
                Alignment.BottomEnd
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GenderScreen() {
    GenderScreenContent(onGenderClick = {}, gender = Gender.Male) {}
}


