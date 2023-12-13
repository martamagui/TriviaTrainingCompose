package com.mmag.triviatraining.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.mmag.domain.model.QuizCategory
import com.mmag.triviatraining.presentation.views.home.HomeScreen
import com.mmag.triviatraining.presentation.viewmodel.HomeViewModel
import com.mmag.triviatraining.presentation.views.ResultScreen
import com.mmag.triviatraining.presentation.views.SplashScreen
import com.mmag.triviatraining.presentation.views.quiz.QuizScreen

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
            SplashScreen(navController = navController)
        }
        composable(route = TriviaTrainingNavigationConfigRoutes.HOME_ROUTE) {
            HomeScreen(navController, viewModel)
        }
        composable(
            route = TriviaTrainingNavigationConfigRoutes.QUIZ_ROUTE,
            arguments = listOf(
                navArgument("category") { type = NavType.IntType },
                navArgument("categoryName") { type = NavType.StringType }
            )
        ) {
            if (it.arguments != null) {
                val category = com.mmag.domain.model.QuizCategory(
                    it.arguments?.getInt("category")!!,
                    it.arguments?.getString("categoryName")!!
                )
                QuizScreen(navController = navController, viewModel, category)
            }
        }
        composable(route = TriviaTrainingNavigationConfigRoutes.RESULT_ROUTE,
            arguments = listOf(
                navArgument("success") { type = NavType.IntType },
                navArgument("questions_amount") { type = NavType.IntType }
            )) {
            if (it.arguments != null) {
                val success = it.arguments?.getInt("success")!!
                val questionAmount = it.arguments?.getInt("questions_amount")!!
                ResultScreen(navController, success, questionAmount, viewModel)
            }
        }
    }
}

