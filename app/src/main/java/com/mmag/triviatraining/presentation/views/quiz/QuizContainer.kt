package com.mmag.triviatraining.presentation.views.quiz

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mmag.triviatraining.presentation.ui.font.fontAgbalumo
import com.mmag.triviatraining.presentation.ui.font.fontExo
import com.mmag.triviatraining.presentation.ui_model.QuizCategory
import com.mmag.triviatraining.presentation.ui_model.QuizQuestion
import com.mmag.triviatraining.presentation.views.HomeViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizContainer(
    navController: NavController,
    viewModel: HomeViewModel,
    category: QuizCategory
) {
    val questionList by viewModel.questionList.collectAsState()
    val pagerState = rememberPagerState(pageCount = { questionList?.size ?: 0 })
    HorizontalPager(state = pagerState) { page ->
        // Our page content
        if (questionList != null) {
            QuestionPage(
                modifier = Modifier.fillMaxSize(),
                onCorrectInteraction = { Log.i("onCorrectInteraction", "onCorrectInteraction") },
                onIncorrectInteraction = { Log.i("onIncorrectInteraction", "MAL") },
                question = questionList?.get(page)!!
            )
        } else {
            Text(text = "No hay preguntas", fontFamily = fontExo)
        }

    }


}

@Composable
fun QuestionPage(
    modifier: Modifier,
    onCorrectInteraction: () -> Unit,
    onIncorrectInteraction: () -> Unit,
    question: QuizQuestion
) {

    Card(modifier = modifier.padding(32.dp)) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            Text(text = question.question, fontFamily = fontExo)
            Spacer(modifier = Modifier.height(24.dp))
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(question.allAnswers) { item ->
                    Button(
                        onClick = {
                            if (question.correctAnswer == item) {
                                onCorrectInteraction()
                            } else {
                                onIncorrectInteraction()
                            }
                        }, modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = item, fontFamily = fontExo)
                    }
                }
            }
        }

    }

}