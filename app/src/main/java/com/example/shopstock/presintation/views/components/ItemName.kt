package com.example.shopstock.presintation.views.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.shopstock.helpers.ItemNameFontSize
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.ItemNameColor

@Composable
fun ItemName(itemName : String){
    Text(
        text = itemName, style = TextStyle(
            color = ItemNameColor,
            fontFamily = Alexandria,
            fontWeight = FontWeight.Bold,
            fontSize = ItemNameFontSize,
        )
    )
}