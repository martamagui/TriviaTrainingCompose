package com.mmag.triviatraining.presentation.views.home


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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.mmag.triviatraining.R
import com.mmag.triviatraining.presentation.TriviaTrainingRouteBuilder
import com.mmag.triviatraining.presentation.ui.styles.categoryTitleStyle
import com.mmag.triviatraining.presentation.ui.theme.Purple50
import com.mmag.triviatraining.presentation.ui.theme.Purple80
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_secondary
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_surfaceVariant
import com.mmag.triviatraining.presentation.viewmodel.HomeViewModel
import com.mmag.triviatraining.presentation.extenions.gradientBackground

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val categoriesState by viewModel.categoriesState.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when (categoriesState) {
            is com.mmag.domain.model.TriviaResponse.Empty -> {
                HomeTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }

            is com.mmag.domain.model.TriviaResponse.Error -> {
                HomeTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
            }

            is com.mmag.domain.model.TriviaResponse.Loading -> {
                HomeTitle(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                )
                CircularProgressIndicator(
                    modifier = Modifier.width(64.dp),
                    color = md_theme_light_surfaceVariant,
                    trackColor = md_theme_dark_secondary,
                )
            }

            is com.mmag.domain.model.TriviaResponse.Success -> {
                if (!categoriesState.data.isNullOrEmpty()) {
                    LazyVerticalGrid(
                        modifier = Modifier.fillMaxWidth(),
                        columns = GridCells.Adaptive(minSize = 180.dp),
                        userScrollEnabled = true
                    ) {

                        item(span = { GridItemSpan(this.maxLineSpan) }) {
                            HomeTitle(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            )
                        }
                        items(categoriesState.data!!, key = { it -> it.id }) { item ->
                            CategoryItem(
                                item = item,
                                modifier = Modifier
                                    .defaultMinSize(minHeight = 180.dp)
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
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategoryItem(item: com.mmag.domain.model.QuizCategory, modifier: Modifier, onClick: () -> Unit) {
    Card(
        onClick = { onClick() },
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .gradientBackground(
                listOf(md_theme_dark_secondary, Purple80, Purple50),
                com.mmag.utils.getRandomGradientAngle()
            ),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Column(
            modifier = modifier
                .padding(12.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = item.name,
                style = categoryTitleStyle
            )
        }

    }
}

@Composable
fun HomeTitle(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.home_title),
        fontSize = 28.sp,
        fontWeight = FontWeight.W500,
        modifier = modifier
    )
}
