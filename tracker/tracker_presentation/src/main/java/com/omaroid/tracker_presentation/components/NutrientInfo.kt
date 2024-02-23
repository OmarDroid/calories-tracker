package com.omaroid.tracker_presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Composable
fun NutrientInfo(
    modifier: Modifier = Modifier,
    name: String,
    amount: Int,
    unit: String,
    amountTextSize: TextUnit = 18.sp,
    amountColor: Color = MaterialTheme.colorScheme.onPrimary,
    unitTextSize: TextUnit = 14.sp,
    unitColor: Color = MaterialTheme.colorScheme.onPrimary,
    nameTextStyle: TextStyle = MaterialTheme.typography.labelSmall
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UnitDisplay(
            amount = amount,
            unit = unit,
            amountTextSize = amountTextSize,
            amountColor = amountColor,
            unitTextSize = unitTextSize,
            unitColor = unitColor
        )
        Text(
            text = name,
            color = MaterialTheme.colorScheme.onPrimary,
            style = nameTextStyle,
            fontSize = 18.sp,
            fontWeight = FontWeight.Light,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NutrientInfoPreview() {
    NutrientInfo(name = "Carbs", amount = 120, unit = "g")
}