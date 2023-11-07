package com.mmag.triviatraining.presentation.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mmag.triviatraining.R
import com.mmag.triviatraining.presentation.TriviaTrainingRouteBuilder
import com.mmag.triviatraining.presentation.ui.font.fontExo
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_secondary
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_surfaceVariant
import com.mmag.triviatraining.presentation.ui_model.QuizCategory

@Composable
fun Home(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val categories by viewModel.categoriesState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.home_greeting),
            fontSize = 28.sp,
            fontWeight = FontWeight.W500,
            modifier = Modifier.padding(12.dp)
        )
        if (categories != null && categories.isNullOrEmpty() == false) {
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                userScrollEnabled = true
            ) {
                items(categories!!) { item ->
                    CategoryItem(item = item) {
                        viewModel.requestCategoryQuestions(item)
                        navController.navigate(
                            TriviaTrainingRouteBuilder.goToQuiz(
                                item.id,
                                item.name
                            )
                        )
                    }
                }
            }
        } else {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = md_theme_light_surfaceVariant,
                trackColor = md_theme_dark_secondary,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(item: QuizCategory, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        modifier = Modifier
            .width(200.dp)
            .height(200.dp)
            .padding(12.dp)
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(text = item.name, fontFamily = fontExo)
        }

    }
}