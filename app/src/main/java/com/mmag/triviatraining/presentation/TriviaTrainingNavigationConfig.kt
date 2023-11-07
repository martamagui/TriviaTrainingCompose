package com.mmag.triviatraining.presentation

import androidx.navigation.NavController

object TriviaTrainingNavigationConfigRoutes {
    const val HOME_ROUTE = "HOME_ROUTE"
    const val SPLASH_ROUTE = "SPLASH_ROUTE"
    const val QUIZ_ROUTE = "QUIZ_ROUTE/{category}/{categoryName}"
    const val RESULT_ROUTE = "RESULT_ROUTE/{success}/{failure}"
}

object TriviaTrainingRouteBuilder {
    fun goToSplash() = "${TriviaTrainingNavigationConfigRoutes.SPLASH_ROUTE}"

    fun goToHome() = "${TriviaTrainingNavigationConfigRoutes.HOME_ROUTE}"

    fun goToQuiz(category: Int, categoryName: String) =
        "${TriviaTrainingNavigationConfigRoutes.QUIZ_ROUTE}"
            .replace("{category}", "$category")
            .replace("{categoryName}", categoryName)

    fun goToResult(success: Int, failure: Int) =
        "${TriviaTrainingNavigationConfigRoutes.RESULT_ROUTE}"
            .replace("{success}", "$success")
            .replace("{failure}", "$failure")


}