package com.omaroid.caloriestracker.navigation

import androidx.navigation.NavController
import com.omaroid.core.util.UiEvent

fun NavController.navigate(event: UiEvent.Navigate) {
    this.navigate(event.route)
}