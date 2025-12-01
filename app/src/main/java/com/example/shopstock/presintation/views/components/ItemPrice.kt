package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import com.example.shopstock.R
import com.example.shopstock.helpers.CardIconSize
import com.example.shopstock.helpers.ItemTextContentFontSize
import com.example.shopstock.helpers.ItemTextFontSize
import com.example.shopstock.helpers.RawSpacedBy
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.ItemContentTextColor
import com.example.shopstock.ui.theme.ItemFontColor

@Composable
fun ItemPrice(itemPrice : Double) {
    Row(verticalAlignment = Alignment.CenterVertically , horizontalArrangement = Arrangement.spacedBy(
        RawSpacedBy
    )) {
        Image(
            painter = painterResource(id = R.drawable.dollar_sign),
            contentDescription = stringResource(R.string.dollar_sign),
            modifier = Modifier.size(CardIconSize)
        )

        Text(
            text = stringResource(R.string.price), style = TextStyle(
                color = ItemFontColor,
                fontFamily = Alexandria,
                fontWeight = FontWeight.Medium,
                fontSize = ItemTextFontSize,
            )
        )

        Text(
            itemPrice.toString(), style = TextStyle(
                color = ItemContentTextColor,
                fontFamily = Alexandria,
                fontSize = ItemTextContentFontSize,
            )
        )
    }
}