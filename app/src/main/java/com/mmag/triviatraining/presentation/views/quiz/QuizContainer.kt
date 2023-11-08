package com.mmag.triviatraining.presentation.views.quiz

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mmag.triviatraining.presentation.TriviaTrainingRouteBuilder
import com.mmag.triviatraining.presentation.common_components.PageIndicator
import com.mmag.triviatraining.presentation.ui.font.fontExo
import com.mmag.triviatraining.presentation.ui.theme.Purple50
import com.mmag.triviatraining.presentation.ui.theme.Purple80
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_onError
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_secondary
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_primary
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_surfaceVariant
import com.mmag.triviatraining.presentation.ui.theme.seed
import com.mmag.triviatraining.presentation.ui_model.QuizCategory
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import com.mmag.triviatraining.presentation.views.HomeViewModel
import com.mmag.triviatraining.utils.getRandomGradientAngle
import com.mmag.triviatraining.utils.gradientBackground
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: HomeViewModel,
    category: QuizCategory
) {
    val questionList by viewModel.questionList.collectAsState()
    val pagerState = rememberPagerState(pageCount = { questionList?.size ?: 0 })
    var successState by rememberSaveable { mutableStateOf(0) }
    val scope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .gradientBackground(
                listOf(md_theme_dark_secondary, Purple80, Purple50),
                getRandomGradientAngle()
            )
            .padding(bottom = 24.dp)
    ) {
        if (!questionList.isNullOrEmpty() && questionList != null) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier
                    .weight(1f)
            ) { page ->
                // Our page content
                QuestionPage(
                    modifier = Modifier.fillMaxWidth(),
                    onInteraction = { value ->
                        if (value) {
                            successState++
                        }
                        onResponseInteraction(
                            successState,
                            scope,
                            pagerState,
                            navController,
                            questionList
                        )
                    },
                    question = questionList?.get(page)!!
                )
            }
            PageIndicator(pagerState)
        } else {
            CircularProgressIndicator(
                modifier = Modifier.width(64.dp),
                color = md_theme_light_surfaceVariant,
                trackColor = md_theme_dark_secondary,
            )
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
private fun onResponseInteraction(
    successState: Int,
    scope: CoroutineScope,
    pagerState: PagerState,
    navController: NavController,
    questionList: List<QuizQuestion>?
) {
    scope.launch {
        delay(400)
        with(pagerState) {
            val target = if (currentPage < pageCount - 1) currentPage + 1 else 0
            if (target != 0) {
                animateScrollToPage(
                    page = target, 0f, animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutSlowInEasing
                    )
                )
            } else {
                val route = TriviaTrainingRouteBuilder.goToResult(
                    successState, questionList!!.size
                )
                navController.navigate(route)
            }
        }
    }
}

@Composable
fun QuestionPage(
    modifier: Modifier,
    onInteraction: (value: Boolean) -> Unit,
    question: QuizQuestion
) {
    Card(modifier = modifier.padding(32.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(text = question.question, fontFamily = fontExo)
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(question.allAnswers) { item ->
                    QuestionButton(
                        item, {
                            onInteraction(question.correctAnswer == item)
                        },
                        Modifier.fillMaxWidth(),
                        question.correctAnswer == item
                    )

                }
            }
        }
    }
}

@Composable
private fun QuestionButton(
    item: String,
    onInteraction: () -> Unit,
    modifier: Modifier,
    isCorrect: Boolean
) {
    val resultColor = if (isCorrect) seed else md_theme_dark_onError
    var isResultClicked by rememberSaveable { mutableStateOf(false) }

    Button(
        onClick = {
            isResultClicked = true
            onInteraction()
        }, modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isResultClicked) {
                resultColor
            } else {
                md_theme_light_primary
            }
        )
    ) {
        Text(text = item, fontFamily = fontExo)
    }
    Spacer(modifier = Modifier.height(8.dp))
}