package com.mmag.triviatraining.presentation.views


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
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
import com.mmag.triviatraining.presentation.ui.styles.titleStyle
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_secondary
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_surfaceVariant
import com.mmag.triviatraining.presentation.ui_model.QuizCategory

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val categories by viewModel.categoriesState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (categories != null && !categories.isNullOrEmpty()) {

            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth(),
                columns = GridCells.Adaptive(minSize = 200.dp),
                userScrollEnabled = true
            ) {

                item(span = { GridItemSpan(this.maxLineSpan) }) {
                    Text(
                        text = stringResource(id = R.string.home_greeting),
                        style = titleStyle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp)
                    )
                }
                items(categories!!) { item ->
                    CategoryItem(
                        item = item,
                        modifier = Modifier
                            .defaultMinSize(minHeight = 200.dp)
                            .fillMaxHeight()
                            .padding(12.dp)
                    ) {
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
            Text(
                text = stringResource(id = R.string.home_greeting),
                fontSize = 28.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(12.dp)
            )
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
fun CategoryItem(item: QuizCategory, modifier: Modifier, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        modifier = modifier
    ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(12.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(text = item.name, fontFamily = fontExo)
        }

    }
}
