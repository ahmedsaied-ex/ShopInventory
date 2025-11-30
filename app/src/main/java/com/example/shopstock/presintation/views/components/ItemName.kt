package com.example.shopstock.presintation.views.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.shopstock.helpers.ItemNameFontSize
import com.example.shopstock.ui.theme.Alexandria

@Composable
fun ItemName(itemName : String){
    Text(
        text = itemName, style = TextStyle(
            color = Color(0xff2e2e2e),
            fontFamily = Alexandria,
            fontWeight = FontWeight.Bold,
            fontSize = ItemNameFontSize,
        )
    )
}