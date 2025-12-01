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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.helpers.BannerImageSize
import com.example.shopstock.helpers.BorderThickness
import com.example.shopstock.helpers.RawSpacedBy
import com.example.shopstock.helpers.RoundedCorners
import com.example.shopstock.helpers.SmallHorizontalPadding
import com.example.shopstock.helpers.SmallVerticalPadding
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
                .padding(horizontal = SmallHorizontalPadding, vertical = SmallVerticalPadding)
        ) {
            Text(
                text = item.inventoryStatus,
                style = TextStyle(
                    fontSize = StockNumberFont,
                    color = itemState.textColor,
                    fontWeight = FontWeight.SemiBold
                )
            )
            Spacer(modifier = Modifier.width(RawSpacedBy))
            Image(
                painter = painterResource(itemState.icon),
                contentDescription = "Status Image",
                modifier = Modifier.size(BannerImageSize)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewInStockBanner() {
    Column {
        InStockBanner(
            item =  ItemEntity(1, "Apple", 100, 25.5, listOf(15, 10, 12, 8, 7, 9, 8))
        )
        Spacer(modifier = Modifier.height(8.dp))
        InStockBanner(  item =ItemEntity(
            id = 41,
            name = "Milk",
            totalQuantity = 400,
            price = 2.0,
            soldLast7Days = listOf(9, 8, 9, 8, 9, 8, 9),

        ) )
        Spacer(modifier = Modifier.height(8.dp))
        InStockBanner(
            item = ItemEntity(16, "Cherry", 400, 10.0, listOf(50, 48, 52, 47, 46, 49, 45)),

            )
    }
}
