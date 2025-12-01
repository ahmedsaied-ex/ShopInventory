package com.example.shopstock.presintation.views.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import com.example.shopstock.helpers.ItemTextFontSize
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.ItemContentTextColor

@Composable
fun ErrorText(error: String) {
    Text(
        "Something went wrong : $error", style = TextStyle(
            color = ItemContentTextColor, fontFamily = Alexandria, fontSize = ItemTextFontSize
        )
    )
}
