package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shopstock.R
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.helpers.CardIconSize
import com.example.shopstock.helpers.ItemTextContentFontSize
import com.example.shopstock.helpers.ItemTextFontSize
import com.example.shopstock.helpers.RawSpacedBy
import com.example.shopstock.ui.theme.Alexandria
import com.example.shopstock.ui.theme.ItemContentTextColor
import com.example.shopstock.ui.theme.ItemFontColor


@Composable
fun InStockRow(
    item: ItemEntity
) {
    Row(
        verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(
            RawSpacedBy)
    ) {
        Image(
            painter = painterResource(id = R.drawable.archive),
            contentDescription = stringResource(R.string.archive_image),
            modifier = Modifier.size(CardIconSize)
        )
        Text(
            text = stringResource(R.string.in_stock), style = TextStyle(
                color = ItemFontColor,
                fontFamily = Alexandria,
                fontWeight = FontWeight.Medium,
                fontSize = ItemTextFontSize,
            )
        )
        InStockBanner(
            item = item
        )


    }
}