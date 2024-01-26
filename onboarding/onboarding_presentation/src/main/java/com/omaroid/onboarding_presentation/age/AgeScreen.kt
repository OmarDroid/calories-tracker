package com.omaroid.onboarding_presentation.age

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.omaroid.core.R
import com.omaroid.core.util.UiEvent
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.onboarding_presentation.components.ActionButton
import com.omaroid.onboarding_presentation.components.UnitTextField

@Composable
fun AgeScreen(
    snackbarHostState: SnackbarHostState,
    onNextClick: () -> Unit,
    viewModel: AgeViewModel = hiltViewModel()
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
    AgeScreenContent(
        age = viewModel.age,
        onAgeChanged = viewModel::onAgeEnter,
        onClick = viewModel::onNextClick
    )
}

@Composable
fun AgeScreenContent(
    age: String,
    onAgeChanged: (String) -> Unit,
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
                text = stringResource(id = R.string.whats_your_age),
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.height(spacing.spaceMedium))
            UnitTextField(
                value = age,
                onValueChanged = onAgeChanged,
                unit = stringResource(id = R.string.years)
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
private fun AgesScreenPreview() {
    AgeScreenContent(
        age = "30",
        onAgeChanged = {},
        onClick = {}
    )
}