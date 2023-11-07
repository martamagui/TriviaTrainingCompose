package com.mmag.triviatraining.presentation.ui.styles

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_onTertiaryContainer
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_primary

val titleStyle = TextStyle(
    fontFamily = FontFamily.SansSerif,
    fontSize = 42.sp,
    fontWeight = FontWeight.Bold,
    textAlign = TextAlign.Center,
    brush = Brush.linearGradient(
        colors = listOf(
            md_theme_light_primary,
            md_theme_light_onTertiaryContainer
        )
    )
)

val categoryTitleStyle = TextStyle(

)