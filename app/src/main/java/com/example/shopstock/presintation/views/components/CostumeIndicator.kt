package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.shopstock.helpers.StartPadding


@Composable
fun CostumeIndicator(itemPercentage : Float){
    LinearProgressIndicator(
        progress = itemPercentage/100,
        color = Color.Black,
        trackColor = Color.LightGray, modifier = Modifier.padding(start =StartPadding)
    )
}