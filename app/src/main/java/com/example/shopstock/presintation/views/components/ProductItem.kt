package com.example.shopstock.presintation.views.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.shopstock.domain.models.ItemEntity
import com.example.shopstock.helpers.BorderThickness



@Composable
fun ProductItem(item: ItemEntity) {
    Card(
        modifier = Modifier
            .padding(bottom = 10.dp)
            .fillMaxWidth()
            .border(
                width = BorderThickness,
                color = Color(0xffe8e8e8),
                shape = RoundedCornerShape(16.dp)
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            ItemName(itemName = item.name)
            ItemPrice(itemPrice = item.price)
            InStockRow(item = item)
            LinearProgressIndicator(
                progress = item.percentage/100,
                color = Color.Black,
                trackColor = Color.LightGray, modifier = Modifier.padding(start = 25.dp)
            )
            PredictSelling(item = item)
            Spacer(modifier = Modifier.height(20.dp))
            SalesLineChart(item.soldLast7Days)
        }
    }
}