package com.omaroid.tracker_presentation.tracker_overview.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.omaroid.core.R
import java.time.LocalDate

@Composable
fun DaySelector(
    modifier: Modifier = Modifier,
    date: LocalDate,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit
) {

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onPreviousClick) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.previous_day)
            )
        }
        Text(
            text = parseDateText(date = date),
            style = MaterialTheme.typography.labelLarge
        )
        IconButton(onClick = onNextClick) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = stringResource(id = R.string.next_day)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DaySelectorPreview() {
    DaySelector(date = LocalDate.now(), onPreviousClick = {}, onNextClick = {})
}