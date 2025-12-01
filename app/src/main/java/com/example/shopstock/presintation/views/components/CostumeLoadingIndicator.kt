package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.shopstock.helpers.CardElevation
import com.example.shopstock.helpers.LoadingIndicatorSize


@Composable
fun LoadingIndicator(){
    Column(Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        CircularProgressIndicator(
            color = Color.Black,
            strokeWidth =CardElevation, modifier = Modifier.size(LoadingIndicatorSize)
        )
    }
}
