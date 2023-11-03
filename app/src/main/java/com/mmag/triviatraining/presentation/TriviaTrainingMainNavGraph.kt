package com.mmag.triviatraining.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mmag.triviatraining.presentation.ui_model.QuizCategory
import com.mmag.triviatraining.presentation.views.Home
import com.mmag.triviatraining.presentation.views.HomeViewModel
import com.mmag.triviatraining.presentation.views.Splash
import com.mmag.triviatraining.presentation.views.quiz.QuizContainer

@Composable
fun TriviaTrainingMainNavGraph(
    navController: NavHostController = rememberNavController(),
    viewModel: HomeViewModel
) {

    NavHost(
        navController = navController,
        startDestination = TriviaTrainingNavigationConfigRoutes.HOME_ROUTE
    ) {
        composable(route = TriviaTrainingNavigationConfigRoutes.SPLASH_ROUTE) {
            Splash(navController = navController)
        }
        composable(route = TriviaTrainingNavigationConfigRoutes.HOME_ROUTE) {
            Home(navController, viewModel)
        }
        composable(
            route = TriviaTrainingNavigationConfigRoutes.QUIZ_ROUTE,
            arguments = listOf(
                navArgument("category") { type = NavType.IntType },
                navArgument("categoryName") { type = NavType.StringType }
            )
        ) {
            if (it.arguments != null) {
                val category = QuizCategory(
                    it.arguments?.getInt("category")!!,
                    it.arguments?.getString("categoryName")!!
                )
                QuizContainer(navController = navController, viewModel, category)
            }

        }
    }
}