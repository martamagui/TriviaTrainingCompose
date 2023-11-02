package com.mmag.triviatraining.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController, viewModel: HomeViewModel) {

    Box(modifier = Modifier
        .background(Color.Magenta)
        .fillMaxSize()) {

    }
}