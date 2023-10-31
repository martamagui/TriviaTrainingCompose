package com.mmag.triviatraining.presentation

import androidx.navigation.NavController

object TriviaTrainingNavigationConfigRoutes {
    const val HOME_ROUTE = "HOME_ROUTE"
    const val SPLASH_ROUTE = "SPLASH_ROUTE"
    const val QUIZ_ROUTE = "QUIZ_ROUTE"
}

class TriviaTrainingNavActions(private val navController: NavController) {
    fun goToSplash() {
        navController.navigate("${TriviaTrainingNavigationConfigRoutes.SPLASH_ROUTE}")
    }

    fun goToHome() {
        navController.navigate("${TriviaTrainingNavigationConfigRoutes.HOME_ROUTE}")
    }

    fun goToQuiz(category: Int) {
        navController.navigate("${TriviaTrainingNavigationConfigRoutes.QUIZ_ROUTE}/{category}")
    }
}