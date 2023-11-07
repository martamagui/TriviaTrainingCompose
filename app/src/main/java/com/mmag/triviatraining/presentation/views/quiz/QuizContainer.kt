package com.mmag.triviatraining.presentation.views.quiz

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mmag.triviatraining.presentation.TriviaTrainingRouteBuilder
import com.mmag.triviatraining.presentation.common_components.PageIndicator
import com.mmag.triviatraining.presentation.ui.font.fontExo
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_onError
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_secondary
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_primary
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_surfaceVariant
import com.mmag.triviatraining.presentation.ui.theme.seed
import com.mmag.triviatraining.presentation.ui_model.QuizCategory
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import com.mmag.triviatraining.presentation.views.HomeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizContainer(
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
            .padding(bottom = 24.dp)
    ) {
        if (!questionList.isNullOrEmpty() && questionList != null) {
            HorizontalPager(
                state = pagerState,
                userScrollEnabled = false,
                modifier = Modifier.weight(1f)
            ) { page ->
                // Our page content
                QuestionPage(
                    modifier = Modifier.fillMaxWidth(),
                    onInteraction = { value ->
                        onResponseInteraction(
                            scope,
                            value,
                            successState,
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
    scope: CoroutineScope,
    value: Boolean,
    successState: Int,
    pagerState: PagerState,
    navController: NavController,
    questionList: List<QuizQuestion>?
) {
    var successState1 = successState
    scope.launch {
        delay(400)
        if (value) {
            successState1++
        }
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
                    successState1, questionList!!.size - successState1
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
                    QuestionButton(question, item, onInteraction, Modifier.fillMaxWidth())
                }
            }
        }

    }

}

@Composable
private fun QuestionButton(
    question: QuizQuestion,
    item: String,
    onInteraction: (value: Boolean) -> Unit,
    modifier: Modifier
) {
    var isResultClicked by rememberSaveable { mutableStateOf(false) }
    val resultColor = if (question.correctAnswer == item) seed else md_theme_dark_onError
    Button(
        onClick = {
            isResultClicked = true
            onInteraction(question.correctAnswer == item)
        }, modifier = modifier
            .background(if (isResultClicked) resultColor else md_theme_light_primary)
    ) {
        Text(text = item, fontFamily = fontExo)
    }
    Spacer(modifier = Modifier.height(8.dp))
}