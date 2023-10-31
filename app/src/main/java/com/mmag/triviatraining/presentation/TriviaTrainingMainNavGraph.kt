package com.mmag.triviatraining.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mmag.triviatraining.presentation.views.Home
import com.mmag.triviatraining.presentation.views.Splash
import com.mmag.triviatraining.presentation.views.quiz.QuizContainer

@Composable
fun TriviaTrainingMainNavGraph(
    navController: NavHostController = rememberNavController(),
    navActions: TriviaTrainingNavActions
) {
    NavHost(
        navController = navController,
        startDestination = TriviaTrainingNavigationConfigRoutes.SPLASH_ROUTE
    ) {
        composable(route =TriviaTrainingNavigationConfigRoutes.SPLASH_ROUTE) {
            Splash(navController = navController)
        }
        composable(route =TriviaTrainingNavigationConfigRoutes.HOME_ROUTE) {
            Home(navController = navController)
        }
        composable(route =TriviaTrainingNavigationConfigRoutes.QUIZ_ROUTE) {
            QuizContainer(navController = navController)
        }
    }
}