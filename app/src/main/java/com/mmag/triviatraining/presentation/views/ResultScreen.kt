package com.mmag.triviatraining.presentation.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.mmag.triviatraining.R
import com.mmag.triviatraining.presentation.ui.theme.Purple50
import com.mmag.triviatraining.presentation.ui.theme.Purple80
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_secondary
import com.mmag.triviatraining.presentation.viewmodel.HomeViewModel
import com.mmag.utils.getRandomGradientAngle
import com.mmag.triviatraining.presentation.extenions.gradientBackground
import com.mmag.utils.toStringFormatted

@Composable
fun ResultScreen(
    navController: NavHostController,
    success: Int,
    questionAmount: Int,
    viewModel: HomeViewModel
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(24.dp)
            .clip(RoundedCornerShape(12.dp))
            .gradientBackground(
                listOf(md_theme_dark_secondary, Purple80, Purple50),
                getRandomGradientAngle()
            ),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val score = ((success.toDouble() / questionAmount) * 10).toStringFormatted()
        Text(
            text = stringResource(id = R.string.result_title, score),
            fontSize = 34.sp,
            lineHeight = 35.sp,
            modifier = Modifier.padding(32.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = stringResource(id = R.string.result_subtitle, "$success", "$questionAmount"),
            fontSize = 24.sp,
            modifier = Modifier.padding(32.dp)
        )
        Spacer(Modifier.weight(1f))
        Button(onClick = { 
            navController.popBackStack()
        }) {
            Text(text = "Go to categories")
        }

    }
}
