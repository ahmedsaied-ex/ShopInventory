package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import com.example.shopstock.R
import com.example.shopstock.helpers.BigVerticalPadding
import com.example.shopstock.helpers.EmptyListImageSize
import com.example.shopstock.helpers.ItemTextFontSize
import com.example.shopstock.helpers.MediumSpacerHeight
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.ItemContentTextColor


@Composable
fun EmptyScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(BigVerticalPadding))
            Image(
                painter = painterResource(id = R.drawable.ic_no_inventory),
                contentDescription = null,
                modifier = Modifier.size(EmptyListImageSize)
            )
            Spacer(modifier = Modifier.height(MediumSpacerHeight))
            Text("No inventory items found", style = TextStyle(
                color = ItemContentTextColor,
                fontFamily = Alexandria,
                fontSize = ItemTextFontSize
            ))
        }
    }
}