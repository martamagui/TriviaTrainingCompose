package com.mmag.triviatraining.presentation.common_components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.mmag.triviatraining.presentation.ui.theme.md_theme_dark_tertiary
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_background
import com.mmag.triviatraining.presentation.ui.theme.md_theme_light_primary

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageIndicator(pagerState: PagerState) {
    LazyRow(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(md_theme_light_primary)
            .padding(4.dp, 2.dp)
    ) {
        items(pagerState.pageCount) {
            val isCurrentPage = it == pagerState.currentPage
            val size = if (isCurrentPage) 11 else 8
            val padding = 1
            val color = if (isCurrentPage) md_theme_light_background else md_theme_dark_tertiary
            DotIndicator(modifier = Modifier.padding(padding.dp), size = size, color = color)
        }
    }
}

@Composable
fun DotIndicator(modifier: Modifier, size: Int, color: Color) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .size(size.dp)
            .background(color)
    )
}
