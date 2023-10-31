package com.mmag.triviatraining.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.mmag.triviatraining.presentation.TriviaTrainingNavigationConfigRoutes.HOME_ROUTE
import com.mmag.triviatraining.presentation.ui.theme.primaryToSecondaryGradient

@Composable
fun Splash(navController: NavController) {
    Box(
        modifier = Modifier
            //.background(primaryToSecondaryGradient)
            .fillMaxSize()
    ) {

        Button(onClick = { navController.navigate(HOME_ROUTE) }) {

        }
    }
}