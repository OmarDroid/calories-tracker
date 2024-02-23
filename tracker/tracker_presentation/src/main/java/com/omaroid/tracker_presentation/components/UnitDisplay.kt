package com.omaroid.tracker_presentation.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.omaroid.core_ui.LocalSpacing

@Composable
fun UnitDisplay(
    modifier: Modifier = Modifier,
    amount: Int,
    unit: String,
    amountTextSize: TextUnit = 16.sp,
    amountColor: Color = MaterialTheme.colorScheme.onPrimary,
    unitTextSize: TextUnit = 14.sp,
    unitColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    val spacing = LocalSpacing.current
    Row(modifier = modifier) {
      Text(
          modifier = Modifier.alignBy(LastBaseline),
          text = amount.toString(),
          style = MaterialTheme.typography.titleLarge,
          fontSize = amountTextSize,
          color = amountColor,
          overflow = TextOverflow.Ellipsis,
      )
      Spacer(modifier = Modifier.width(spacing.spaceExtraSmall))
        Text(
            modifier = Modifier.alignBy(LastBaseline),
            text = unit,
            style = MaterialTheme.typography.labelMedium,
            fontSize = unitTextSize,
            color = unitColor,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview
@Composable
private fun UnitDisplayPreview() {
    UnitDisplay(amount = 120, unit = "Kg")
}