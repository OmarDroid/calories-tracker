package com.omaroid.caloriestracker

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omaroid.caloriestracker.ui.theme.CaloriesTrackerTheme
import com.omaroid.core.navigation.Route
import com.omaroid.onboarding_presentation.gender.GenderScreen
import com.omaroid.onboarding_presentation.welcome.WelcomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaloriesTrackerTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController, startDestination = Route.WELCOME
                ) {
                    composable(Route.WELCOME) {
                        WelcomeScreen(onNextClick = {
                            navController.navigate(Route.GENDER)
                        })
                    }
                    composable(Route.GENDER) {
                        GenderScreen(onNextClick = {
                            navController.navigate(Route.WELCOME)
                        })
                    }
                    composable(Route.AGE) {

                    }
                    composable(Route.HEIGHT) {

                    }
                    composable(Route.WEIGHT) {

                    }
                    composable(Route.NUTRIENT_GOAL) {

                    }
                    composable(Route.ACTIVITY) {

                    }
                    composable(Route.GOAL) {

                    }
                    composable(Route.TRACKER_OVERVIEW) {

                    }
                    composable(Route.SEARCH) {

                    }

                }
            }
        }
    }
}