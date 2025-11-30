package com.example.shopstock.domain.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class ChartStyle(
    val lineColor: Color = Color.Black,
    val areaColor: Color = Color.LightGray.copy(alpha = 0.4f),
    val dotColor: Color = Color.Black,
    val lineWidth: Dp = 2.dp,
    val dotRadius: Dp = 4.dp
)