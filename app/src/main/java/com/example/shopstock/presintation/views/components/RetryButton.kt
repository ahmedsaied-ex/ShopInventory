package com.example.shopstock.presintation.views.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.example.shopstock.helpers.ItemTextFontSize
import com.example.shopstock.ui.theme.Alexandria


@Composable
fun RetryButton(onClick:()->Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(
            Color.Black.copy(alpha = 0.9f)
        ), onClick = {
            onClick()
        }) {
        Text(
            "Retry", style = TextStyle(
                color = Color.White, fontFamily = Alexandria, fontSize = ItemTextFontSize
            )
        )
    }
}