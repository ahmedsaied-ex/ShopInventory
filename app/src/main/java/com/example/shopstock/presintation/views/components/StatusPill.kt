package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopstock.R
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.helpers.BorderThickness
import com.example.shopstock.helpers.RoundedCorners
import com.example.shopstock.helpers.StockNumberFont
import com.example.shopstock.helpers.toStatusRaw


@Composable
fun InStockBanner( item: ItemEntity) {
    val itemState = item.state.toStatusRaw()
    Box(
        modifier = Modifier,
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .border(
                    width = BorderThickness,
                    color = itemState.borderColor,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(RoundedCorners)
                )
                .background(
                    color = itemState.backgroundColor,
                    shape = androidx.compose.foundation.shape.RoundedCornerShape(RoundedCorners)
                )
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = item.inventoryStatus,
                style = TextStyle(
                    fontSize = StockNumberFont,
                    color = itemState.textColor,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.width(4.dp))
            Image(
                painter = painterResource(itemState.icon),
                contentDescription = "",
                modifier = Modifier.size(16.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInStockBanner() {
    Column {
        InStockBanner(  item =ItemEntity(
            id = 41,
            name = "Milk",
            totalQuantity = 400,
            price = 2.0,
            soldLast7Days = listOf(9, 8, 9, 8, 9, 8, 9),

        ) )
        Spacer(modifier = Modifier.height(8.dp))
        InStockBanner(  item =ItemEntity(
            id = 41,
            name = "Milk",
            totalQuantity = 400,
            price = 2.0,
            soldLast7Days = listOf(9, 8, 9, 8, 9, 8, 9),

        ) )
        Spacer(modifier = Modifier.height(8.dp))
        InStockBanner(  item =ItemEntity(
            id = 41,
            name = "Milk",
            totalQuantity = 400,
            price = 2.0,
            soldLast7Days = listOf(9, 8, 9, 8, 9, 8, 9),

        ) )
    }
}
