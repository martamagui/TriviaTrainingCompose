package com.mmag.triviatraining.presentation.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.mmag.triviatraining.presentation.TriviaTrainingNavigationConfigRoutes.HOME_ROUTE

@Composable
fun SplashScreen(navController: NavController) {
    Box(
        modifier = Modifier
            //.background(primaryToSecondaryGradient)
            .fillMaxSize()
    ) {

        Button(onClick = { navController.navigate(HOME_ROUTE) }) {

        }
    }
}