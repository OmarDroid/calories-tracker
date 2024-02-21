package com.omaroid.caloriestracker.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.omaroid.core_ui.BrightGreen
import com.omaroid.core_ui.DarkGray
import com.omaroid.core_ui.DarkGreen
import com.omaroid.core_ui.BrightLightGreen
import com.omaroid.core_ui.Dimensions
import com.omaroid.core_ui.LightGray
import com.omaroid.core_ui.LightGreen
import com.omaroid.core_ui.LocalSpacing
import com.omaroid.core_ui.MediumGray
import com.omaroid.core_ui.Orange
import com.omaroid.core_ui.TextWhite

private val DarkColorScheme = darkColorScheme(
    primary = BrightGreen,
    onPrimary = DarkGreen,
    secondary = Orange,
    background = MediumGray,
    onBackground = TextWhite,
    surface = LightGray,
    onSurface = TextWhite,
    primaryContainer = Color.White,
    onSecondary = Color.White,
    onSurfaceVariant = BrightLightGreen
)

private val LightColorScheme = lightColorScheme(
    primary = BrightGreen,
    onPrimary = DarkGreen,
    secondary = Orange,
    background = LightGreen,
    onBackground = DarkGray,
    surface = Color.White,
    onSurface = DarkGray,
    primaryContainer = Color.White,
    onSecondary = Color.White,
    onSurfaceVariant = BrightLightGreen

    /* Other default colors to override
    background = Color(0xFFFFFBFE),
    surface = Color(0xFFFFFBFE),
    onPrimary = Color.White,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color(0xFF1C1B1F),
    onSurface = Color(0xFF1C1B1F),
    */
)

@Composable
fun CaloriesTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    CompositionLocalProvider(LocalSpacing provides Dimensions()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content
        )
    }
}