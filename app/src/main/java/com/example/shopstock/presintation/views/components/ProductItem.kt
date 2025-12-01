package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.helpers.BigSpacerHeight
import com.example.shopstock.helpers.CardElevation
import com.example.shopstock.helpers.IconSize
import com.example.shopstock.helpers.RawSpacedBy
import com.example.shopstock.helpers.RoundedCorners


@Composable
fun ProductItem(item: ItemEntity) {
    Card(
        modifier = Modifier
            .padding(bottom = RoundedCorners)
            .fillMaxWidth(),
        shape = RoundedCornerShape(RoundedCorners),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = CardElevation)
    ) {
        Column(
            modifier = Modifier.padding(IconSize),
            verticalArrangement = Arrangement.spacedBy(
                RawSpacedBy
            )
        ) {
            ItemName(itemName = item.name)
            ItemPrice(itemPrice = item.price)
            InStockRow(item = item)
            CostumeIndicator(itemPercentage = item.percentage)
            PredictSelling(item = item)
            Spacer(modifier = Modifier.height(BigSpacerHeight))
            SalesLineChart(item.soldLast7Days)
        }
    }


}
